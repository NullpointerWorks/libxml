/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml;

import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.XMLLoaderType;
import exp.nullpointerworks.xml.io.dom.DOMLoader;
import exp.nullpointerworks.xml.io.dom.StreamLoader;

public class XMLLoaderFactory
{	
	/**
	 * 
	 */
	public static DocumentLoader getLoader(XMLLoaderType type)
	{
		switch(type)
		{
		case DOM: return new DOMLoader(null);
		case STREAM: return new StreamLoader(null);
		case SAX: break;
		default: break;
		}
		
		return new DOMLoader(null);
	}
	
	/**
	 * 
	 */
	public static DocumentLoader getLoader(XMLLoaderType type, Document doc)
	{
		switch(type)
		{
		case DOM: return new DOMLoader(doc);
		case STREAM: return new StreamLoader(doc);
		case SAX: break;
		default: break;
		}
		
		return new DOMLoader(doc);
	}
}
