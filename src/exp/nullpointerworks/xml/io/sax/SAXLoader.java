/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io.sax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.XMLLoaderType;

/**
 * Simple API for XML (SAX). Streamed unidirectional loading with events.
 */
public class SAXLoader implements DocumentLoader, SAXEventListener
{
	private SAXEventListener saxel;
	private Document doc = null;
	
	public SAXLoader()
	{
		saxel = this;
	}
	
	public SAXLoader(SAXEventListener l)
	{
		saxel = l;
	}
	
	@Override
	public XMLLoaderType getLoaderType() 
	{
		return XMLLoaderType.SAX;
	}
	
	@Override
	public Document getDocument() 
	{
		return doc;
	}
	
	@Override
	public Document parse(String path) throws FileNotFoundException, XMLParseException 
	{
		File lfile = new File(path);
		if (!lfile.exists()) throw new FileNotFoundException();
	    InputStream stream = new FileInputStream(lfile);
		return parse(stream);
	}
	
	@Override
	public Document parse(InputStream is) throws XMLParseException 
	{
		
		
		
		
		
		return null;
	}
	
	// ===============================================================================
	
	@Override
	public void onDocumentStart() 
	{
		doc = new Document();
	}
	
	@Override
	public void onDocumentEnd() 
	{
		
	}

	@Override
	public void onElementStart(String xmlPath, String eName, Attributes attrs) 
	{
		
	}

	@Override
	public void onElementEnd(String xmlPath, String eName) 
	{
		
	}
}
