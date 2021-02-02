package exp.nullpointerworks.xml.example;

import java.io.FileNotFoundException;

import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.sax.SAXEventListener;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;
import exp.nullpointerworks.xml.io.sax.SAXDocumentLoader;

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
	
	private Document doc;
	private Element root;
	
	public MainExample2()
	{
		/*
		 * load the file from the first example using a SAX event loader
		 */
		final String path = "bin/example1.xml";
		DocumentLoader dl = new SAXDocumentLoader(this);
		
		try 
		{
			dl.parse(path);
		} 
		catch (FileNotFoundException | XMLParseException e) 
		{
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void onDocumentStart() 
	{
		doc = new Document();
	}

	@Override
	public void onDocumentEnd() 
	{
		
	}

	@Override
	public void onDocumentProlog(Attributes attrs) 
	{
		Prolog pr = new XMLProlog();
		var it = attrs.getIterator();
		while(it.hasNext())
		{
			var att = it.getNext();
			System.out.println( att.getString() );
			pr.addAttribute(att);
		}
	}
	
	@Override
	public void onElementStart(String xmlPath, String eName, Attributes attrs) 
	{
		
		
		
	}

	@Override
	public void onElementEnd(String xmlPath, String eName) 
	{
		
	}

	@Override
	public void onCharacter(char c) 
	{
		
	}
}
