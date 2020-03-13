/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;

public class DocumentIO 
{
	/**
	 * load using a DOM loader
	 */
	public static Document read(String path) throws XMLParseException, IOException 
	{
		XMLLoader dl = DocumentBuilder.getDOMLoader();
		Document doc = dl.parse(path);
		return doc;
	}
	
	/*
	 * load using a SAX stream loader
	 */
	public static Document stream(InputStream is) throws XMLParseException 
	{
		return null;
	}
	
	/*
	 * write to disc
	 */
	public static void write(Document doc, String path) throws IOException
	{
		write(doc, path, null );
	}
	
	public static void write(Document doc, String path, Format format) throws IOException
	{
		DocumentWriter dw = new DocumentWriter(format);
		dw.write(doc, path);
	}
	
}
