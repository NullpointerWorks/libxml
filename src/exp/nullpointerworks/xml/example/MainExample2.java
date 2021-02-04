package exp.nullpointerworks.xml.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nullpointerworks.util.pattern.Iterator;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.FormatFactory;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.io.DocumentWriter;
import exp.nullpointerworks.xml.io.SAXDocumentLoader;
import exp.nullpointerworks.xml.io.SAXEventListener;
import exp.nullpointerworks.xml.io.SAXLoader;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

/**
 * 
 * @author Michiel Drost - Nullpointer Works
 */
public class MainExample2 implements SAXEventListener
{
	public static void main(String[] args) 
	{
		/*
		 * lets run the first example to generate an XML file
		 */
		//MainExample1.main(args);
		new MainExample2();
	}
	
	public MainExample2()
	{
		final String path1 = "bin/example1.xml";
		final String path2 = "bin/example2.xml";
		
		/*
		 * load the file from the first example using a SAX event loader
		 * 
		 * when not specifying your own event listener a default will be used.
		 * this stored a DOM object 
		 */
		SAXLoader sl = new SAXDocumentLoader(this);
		
		try 
		{
			sl.parse(path1);
		} 
		catch (FileNotFoundException | XMLParseException e) 
		{
			e.printStackTrace();
			return;
		}
		
		final Format format = FormatFactory.getPrettyWindowsFormat();
		final DocumentWriter dw = new DocumentWriter(format);
		
		// safe a copy of the read file
		try
		{
			dw.write(document, path2);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private Document document;
	private Element current;
	private List<Element> elementPath = new ArrayList<Element>();
	
	@Override
	public void onDocumentStart() 
	{
		document = new Document();
		current = new Element("xml");
		elementPath.clear();
		elementPath.add(current);
	}
	
	@Override
	public void onDocumentEnd() 
	{
		Element root = elementPath.get(0).getChild(0);
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
	public void onElementStart(String xmlPath, String eName, Attributes attrs) 
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
		elementPath.add(el);
	}
	
	@Override
	public void onElementEnd(String xmlPath, String eName) 
	{
		elementPath.remove( elementPath.size()-1 );
		current = elementPath.get( elementPath.size()-1 );
	}
	
	@Override
	public void onCharacter(String xmlPath, String s)
	{
		if (s.equals("\t")) return;
		if (s.equals("\r")) return;
		if (s.equals("\n")) return;
		
		String t = current.getText();
		if (t==null)t="";
		current.setText( t + s );
	}
}
