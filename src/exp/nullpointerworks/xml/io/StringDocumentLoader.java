/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2022)
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.nullpointerworks.util.pattern.Iterator;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.util.CharacterParser;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

/**
 * This implementation of a DOM loader is meant to be used when a string containing XML code needs to be parsed outside of a file or stream.
 * 
 * @author Michiel Drost - Nullpointer Works
 */
public class StringDocumentLoader extends CharacterParser implements DOMLoader
{
	private List<Element> path = new ArrayList<Element>();
	private Document document;
	private Element current;
	
	@Override
	public void setDocument(Document d)
	{
		if (d!=null) document = d;
	}
	
	@Override
	public Document getDocument()
	{
		return document;
	}
	
	@Override
	public Document parse(String str) throws XMLParseException
	{
		onDocumentStart();
		
		int i=0;
		int l=str.length();
		for (; i<l;i++)
		{
			String chr = str.substring(i,i+1);
			nextCharacter(chr);
		}
		
		onDocumentEnd();
		
		return document;
	}
	
	@Override
	public Document parse(InputStream fis) throws XMLParseException
	{
		
		String cont = "";
		try 
		{
			cont = readContent(fis);
		}
		catch (IOException e) 
		{
			throw new XMLParseException(e);
		}
		
		try
		{
			fis.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return parse(cont);
	}
	
	private String readContent(InputStream fis) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		while (fis.available() > 0) 
		{
			String chr = ""+( (char)fis.read() );
			sb.append(chr);
		}
		return sb.toString();
	}
	
	// ====================================================================================
	
	@Override
	protected final void onDocumentStart() 
	{
		if (document==null) document = new Document();
		current = new Element("xml");
		path.clear();
		path.add(current);
	}
	
	@Override
	protected final void onDocumentEnd() 
	{
		Element root = path.get(0).getChild(0);
		document.setRootElement(root);
	}
	
	@Override
	protected final void onDocumentProlog(Attributes attrs) 
	{
		Prolog pr = new XMLProlog();
		Iterator<Attribute> it = attrs.getIterator();
		while (it.hasNext())
		{
			Attribute a = it.getNext();
			pr.addAttribute(a);
		}
		document.setProlog(pr);
	}
	
	@Override
	protected final void onElementStart(String eName, Attributes attrs) 
	{
		Element el = new Element(eName);
		Iterator<Attribute> it = attrs.getIterator();
		while (it.hasNext())
		{
			Attribute a = it.getNext();
			el.addAttribute(a);
		}
		current.addChild(el);
		current = el;
		path.add(el);
	}
	
	@Override
	protected final void onElementEnd(String eName) 
	{
		path.remove( path.size()-1 );
		current = path.get( path.size()-1 );
	}
	
	@Override
	protected final void onCharacter(String s)
	{
		if (s.equals("\t")) return;
		if (s.equals("\r")) return;
		if (s.equals("\n")) return;
		
		String t = current.getText();
		if (t==null)t="";
		current.setText( t + s );
	}
}
