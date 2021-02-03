package exp.nullpointerworks.xml.io.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import exp.nullpointerworks.xml.io.DOMLoader;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

public class DOM2DocumentLoader extends XMLCharacterParser implements DOMLoader
{
	private List<Element> path = new ArrayList<Element>();
	private Document document;
	private Element current;
	
	public DOM2DocumentLoader()
	{
		reset();
		document = new Document();
	}
	
	public DOM2DocumentLoader(Document d)
	{
		this();
		if (d!=null) document = d;
		setDocument(document);
	}
	
	// ====================================================================================
	
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
	public Document parse(String path) throws FileNotFoundException, XMLParseException 
	{
		File initialFile = new File(path);
	    InputStream stream;
	    stream = new FileInputStream(initialFile);
		return parse(stream);
	}
	
	@Override
	public Document parse(InputStream fis) throws XMLParseException
	{
		try 
		{
			parseContent(document, fis);
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
		
		return document;
	}
	
	private void parseContent(Document doc, InputStream fis) throws XMLParseException, IOException
	{
		while (fis.available() > 0) 
		{
			String chr = ""+( (char)fis.read() );
			nextCharacter(chr);
		}
	}
	
	// ====================================================================================
	
	@Override
	void onDocumentStart() 
	{
		document = new Document();
		current = new Element("xml");
		
		path.clear();
		path.add(current);
	}

	@Override
	void onDocumentEnd() 
	{
		Element root = path.get(0).getChild(0);
		document.setRootElement(root);
	}
	
	@Override
	public void onDocumentProlog(Attributes attrs) 
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
	public void onElementStart(String eName, Attributes attrs) 
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
	public void onElementEnd(String eName) 
	{
		path.remove( path.size()-1 );
		current = path.get( path.size()-1 );
	}
	
	@Override
	public void onCharacter(char c)
	{
		String s = ""+c;
		if (s.equals("\t")) return;
		if (s.equals("\r")) return;
		if (s.equals("\n")) return;
		current.setText( current.getText() + s );
	}
}
