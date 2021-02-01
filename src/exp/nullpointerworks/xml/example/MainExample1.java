package exp.nullpointerworks.xml.example;

import java.io.File;
import java.io.IOException;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.Encoding;
import exp.nullpointerworks.xml.FormatFactory;
import exp.nullpointerworks.xml.LoaderFactory;
import exp.nullpointerworks.xml.StandAlone;
import exp.nullpointerworks.xml.Version;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.io.DocumentIO;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

/**
 * 
 * @author Michiel Drost - Nullpointer Works
 */
public class MainExample1 
{
	public static void main(String[] args)
	{
		/*
		 * we need a path to the file, obviously
		 * when creating a document we need a prolog.
		 * and when saving a document we need a formatting instance
		 */
		final String path = "bin/example1.xml";
		final Prolog prolog = new XMLProlog(Version.V10, Encoding.UTF8, StandAlone.NO);
		final Format format = FormatFactory.getPrettyWindowsFormat();
		
		/*
		 * make a default file if it's not present
		 */
		File f = new File(path);
		if (!f.exists())
		{
			// make a new empty document instance.
			// set the root element
			// and set an XML prolog
			Document doc = new Document();
			doc.setRootElement( new Element("root") );
			doc.setProlog(prolog);
			
			// write the document to disk using a formatting object
			try
			{
				DocumentIO.write(doc, path, format);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
		
		/*
		 * add some information to an existing document
		 * load an XML file into a Document object
		 */
		DocumentLoader dl = LoaderFactory.getDOMLoader();
		Document doc = null;
		
		try
		{
			doc = dl.parse( path );
		} 
		catch (XMLParseException | IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		/*
		 * setting the text on an element adds a Text object and sets 
		 * its content. If an instance of a Text element is already
		 * present, it will modify it's content instead.
		 * 
		 * In other words, the text on an element is always the first 
		 * child element of the Text subclass.
		 */
		Element person = new Element("sometext");
		person.setText("This is some example text.");
		
		Element root = doc.getRootElement();
		root.addChild(person);
		
		// don't forget to save
		try
		{
			DocumentIO.write(doc, path, format);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}