/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.util.CharacterParser;
import exp.nullpointerworks.xml.io.util.TagPath;

/**
 * Simple API for XML (SAX). Streamed unidirectional loading with events.
 */
public class SAXDocumentLoader extends CharacterParser implements SAXLoader
{
	private List<SAXEventListener> saxel;
	private TagPath tagPath;
	
	public SAXDocumentLoader(SAXEventListener l)
	{
		tagPath = new TagPath();
		saxel = new ArrayList<SAXEventListener>();
		saxel.add(l);
	}
	
	// ===============================================================================
	
	public void addEventListener(SAXEventListener el)
	{
		if (!saxel.contains(el)) saxel.add(el);
	}
	
	public void removeEventListener(SAXEventListener el)
	{
		if (saxel.contains(el)) saxel.remove(el);
	}
	
	public void clearEventListeners()
	{
		saxel.clear();
	}
	
	@Override
	public void parse(String path) throws FileNotFoundException, XMLParseException 
	{
		File lfile = new File(path);
		if (!lfile.exists()) throw new FileNotFoundException();
	    InputStream stream = new FileInputStream(lfile);
		parse(stream);
	}
	
	@Override
	public void parse(InputStream fis) throws XMLParseException
	{
		tagPath.push("xml");
		onDocumentStart();
		try 
		{
			parseContent(fis);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		onDocumentEnd();
		
		try 
		{
			fis.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void parseContent(InputStream fis) throws XMLParseException, IOException
	{
		while (fis.available() > 0) 
		{
			String chr = ""+( (char)fis.read() );
			nextCharacter(chr);
		}
	}
	
	// ===============================================================================
	
	@Override
	protected void onDocumentStart() 
	{
		for (SAXEventListener el : saxel)
		{
			el.onDocumentStart();
		}
	}
	
	@Override
	protected void onDocumentEnd() 
	{
		for (SAXEventListener el : saxel)
		{
			el.onDocumentEnd();
		}
	}
	
	@Override
	protected void onDocumentProlog(Attributes attrs) 
	{
		for (SAXEventListener el : saxel)
		{
			el.onDocumentProlog(attrs);
		}
	}
	
	@Override
	protected void onElementStart(String eName, Attributes attrs) 
	{
		for (SAXEventListener el : saxel)
		{
			el.onElementStart(tagPath.getPath(), eName, attrs);
		}
		tagPath.push(eName);
	}
	
	@Override
	protected void onElementEnd(String eName) 
	{
		tagPath.pop();
		for (SAXEventListener el : saxel)
		{
			el.onElementEnd(tagPath.getPath(), eName);
		}
	}
	
	@Override
	protected void onCharacter(String c) 
	{
		for (SAXEventListener el : saxel)
		{
			el.onCharacter(tagPath.getPath(), c);
		}
	}
}
