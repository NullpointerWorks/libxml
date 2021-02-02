/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.io.dom.DOMDocumentLoader;
import exp.nullpointerworks.xml.io.dom.StreamDocumentLoader;

public class DocumentIO 
{
	// ========================================================================
	// 		reading 
	// ========================================================================
	
	/**
	 * load using a DOM loader
	 */
	public static Document read(String path) throws XMLParseException, IOException 
	{
		DocumentLoader dl = new DOMDocumentLoader();
		Document doc = dl.parse(path);
		return doc;
	}
	
	/**
	 * load using a DOM stream loader
	 */
	public static Document stream(InputStream is) throws XMLParseException 
	{
		DocumentLoader dl = new StreamDocumentLoader();
		Document doc = dl.parse(is);
		return doc;
	}
	
	// ========================================================================
	// 		writing 
	// ========================================================================
	
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
