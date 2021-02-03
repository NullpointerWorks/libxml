package exp.nullpointerworks.xml.example;

import java.io.FileNotFoundException;
import java.io.IOException;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.FormatFactory;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.io.DocumentIO;
import exp.nullpointerworks.xml.io.DocumentLoader;
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
		 * 
		 * when not specifying your own event listener a default will be used.
		 * this stored a DOM object 
		 */
		final String path1 = "bin/example1.xml";
		final String path2 = "bin/example2.xml";
		DocumentLoader dl = new SAXDocumentLoader();
		
		try 
		{
			dl.parse(path1);
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
}
