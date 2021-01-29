package exp.nullpointerworks.xml.example;

import java.io.File;
import java.io.IOException;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.Encoding;
import exp.nullpointerworks.xml.LoaderFactory;
import exp.nullpointerworks.xml.StandAlone;
import exp.nullpointerworks.xml.Version;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.format.FormatFactory;
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
		 * and when creating a new file we need a prolog and a formatting instance
		 */
		final String path = "bin/example1.xml";
		final Format format = FormatFactory.getPrettyWindowsFormat();
		final Prolog prolog = new XMLProlog(Version.V10, Encoding.UTF8, StandAlone.NO);
		
		/*
		 * lets check if the file and directory exists. if not, make one
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
		 * add some information to the document
		 */
		Element root = doc.getRootElement();
		
		Element person = new Element();
		
		
		root.addChild(person);
		
		
		
		
	}
}
