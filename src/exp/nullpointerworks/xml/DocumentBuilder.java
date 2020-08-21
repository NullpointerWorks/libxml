/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2020)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml;

import exp.nullpointerworks.xml.io.DOMLoader;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.StreamLoader;

public class DocumentBuilder
{	
	/**
	 * Returns a DOM loader
	 */
	public static DocumentLoader getDOMLoader()
	{
		return new DOMLoader(null);
	}
	
	/**
	 * Returns a DOM loader
	 */
	public static DocumentLoader getDOMLoader(Document doc)
	{
		return new DOMLoader(doc);
	}
	
	/**
	 * Returns a DOM loader using a file input stream.
	 */
	public static DocumentLoader getStreamLoader()
	{
		return new StreamLoader(null);
	}
	
	/**
	 * Returns a DOM loader using a file input stream.
	 */
	public static DocumentLoader getStreamLoader(Document doc)
	{
		return new StreamLoader(doc);
	}
	
}
