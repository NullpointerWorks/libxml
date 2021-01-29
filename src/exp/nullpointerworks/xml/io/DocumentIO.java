/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2020)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.LoaderFactory;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.Format;

public class DocumentIO 
{
	// ========================================================================
	// 		memory loading
	// ========================================================================
	
	/**
	 * load using a DOM loader
	 */
	public static Document read(String path) throws XMLParseException, IOException 
	{
		DocumentLoader dl = LoaderFactory.getDOMLoader();
		Document doc = dl.parse(path);
		return doc;
	}
	
	/**
	 * load using a specified loader
	 */
	public static Document read(String path, XMLLoaderType type) throws XMLParseException, IOException 
	{
		DocumentLoader dl = null;
		switch(type)
		{
		case STREAM:
			dl = LoaderFactory.getStreamLoader();
			break;
		case DOM:
		default:
			dl = LoaderFactory.getDOMLoader();
			break;
		}
		Document doc = dl.parse(path);
		return doc;
	}
	
	// ========================================================================
	// 		stream loading
	// ========================================================================
	
	/**
	 * load using a DOM stream loader
	 */
	public static Document stream(InputStream is) throws XMLParseException 
	{
		DocumentLoader dl = LoaderFactory.getDOMLoader();
		Document doc = dl.parse(is);
		return doc;
	}
	
	/**
	 * stream using a specified loader
	 */
	public static Document stream(InputStream is, XMLLoaderType type) throws XMLParseException, IOException 
	{
		DocumentLoader dl = null;
		switch(type)
		{
		case STREAM:
			dl = LoaderFactory.getStreamLoader();
			break;
		case DOM:
		default:
			dl = LoaderFactory.getDOMLoader();
			break;
		}
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
