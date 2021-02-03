package exp.nullpointerworks.xml.example;

import java.io.FileNotFoundException;
import java.io.IOException;

import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.FormatFactory;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.io.DocumentIO;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.sax.SAXEventListener;
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
		final String path2 = "bin/example2.xml";
		DocumentLoader dl = new SAXDocumentLoader();
		
		try 
		{
			dl.parse(path);
		} 
		catch (FileNotFoundException | XMLParseException e) 
		{
			e.printStackTrace();
			return;
		}
		
		Document doc = dl.getDocument();
		
		final Format format = FormatFactory.getPrettyWindowsFormat();
		try
		{
			DocumentIO.write(doc, path2, format);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDocumentStart() 
	{
		
	}

	@Override
	public void onDocumentEnd() 
	{
		
	}

	@Override
	public void onDocumentProlog(Attributes attrs) 
	{
		
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
	public void onCharacter(String xmlPath, char c) 
	{
		
	}
}
