/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;

/**
 * Simple API for XML (SAX). Streamed unidirectional loading with events.
 */
public class SAXLoader implements DocumentLoader 
{
	private SAXEventListener saxel;
	private Document doc;
	
	public SAXLoader()
	{
		
	}
	
	@Override
	public XMLLoaderType getLoaderType() 
	{
		return XMLLoaderType.SAX;
	}
	
	@Override
	public Document getDocument() 
	{
		return null;
	}
	
	@Override
	public Document parse(String path) throws FileNotFoundException, XMLParseException 
	{
		return null;
	}
	
	@Override
	public Document parse(InputStream is) throws XMLParseException 
	{
		return null;
	}
	
}
