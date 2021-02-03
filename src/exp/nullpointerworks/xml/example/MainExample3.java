package exp.nullpointerworks.xml.example;

import java.io.FileNotFoundException;

import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.sax.SAXEventListener;
import exp.nullpointerworks.xml.io.sax.SAXDocumentLoader;

/**
 * 
 * @author Michiel Drost - Nullpointer Works
 */
public class MainExample3 implements SAXEventListener
{
	public static void main(String[] args) 
	{
		/*
		 * lets run the first example to generate an XML file
		 */
		//MainExample1.main(args);
		new MainExample3();
	}
	
	public MainExample3()
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
