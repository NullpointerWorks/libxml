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
public class MainExample2
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
		/*
		 * load the file from the first example using a SAX event loader
		 */
		final String path = "bin/example1.xml";
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
		
		
	}
}
