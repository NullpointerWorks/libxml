/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io.sax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.XMLBadPrologException;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.SAXLoader;

/**
 * Simple API for XML (SAX). Streamed unidirectional loading with events.
 */
public class SAXDocumentLoader implements SAXLoader, SAXEventListener
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
		if (saxel == null) throw new XMLParseException(null);
		
		tagPath.push("xml");
		onDocumentStart();
		try 
		{
			parseStream(fis);
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
	
	// ===============================================================================
	
	@Override
	public void onDocumentStart() 
	{
		for (SAXEventListener el : saxel)
		{
			el.onDocumentStart();
		}
	}
	
	@Override
	public void onDocumentEnd() 
	{
		for (SAXEventListener el : saxel)
		{
			el.onDocumentEnd();
		}
	}
	
	@Override
	public void onDocumentProlog(Attributes attrs) 
	{
		for (SAXEventListener el : saxel)
		{
			el.onDocumentProlog(attrs);
		}
	}
	
	@Override
	public void onElementStart(String xmlPath, String eName, Attributes attrs) 
	{
		for (SAXEventListener el : saxel)
		{
			el.onElementStart(xmlPath, eName, attrs);
		}
	}
	
	@Override
	public void onElementEnd(String xmlPath, String eName) 
	{
		for (SAXEventListener el : saxel)
		{
			el.onElementEnd(xmlPath, eName);
		}
	}
	
	@Override
	public void onCharacter(String xmlPath, char c) 
	{
		for (SAXEventListener el : saxel)
		{
			el.onCharacter(xmlPath, c);
		}
	}
	
	// ===============================================================================
	
	private void parseProlog(String line) throws XMLBadPrologException 
	{
		if (line.startsWith("?"))
		{
			if (line.startsWith("? ")) throw new XMLBadPrologException(null);
			if (!line.endsWith("?")) throw new XMLBadPrologException(null);
			
			Attributes attrs = new Attributes();
			String[] tokens = line.split(" ");
			String prologType = tokens[0];
			
			if (prologType.equalsIgnoreCase("?xml"))
			{
				for (int i=1,l=tokens.length-1; i<l; i++)
				{
					String att = tokens[i];
					attrs.addAttribute( new Attribute().setAttribute(att) );
				}
			}
			onDocumentProlog(attrs);
		}
	}
	
	private void parseTag(String line) 
	{
		if (isTagOpening(line))
		{
			String tagName = line.split(" ")[0];
			Attributes attrs = findAttributes(line);
			onElementStart(tagPath.getPath(), tagName, attrs);
			tagPath.push(tagName);
			return;
		}
		
		if (isTagClosing(line))
		{
			String tagName = line.substring(1).trim();
			tagPath.pop();
			onElementEnd(tagPath.getPath(), tagName);
			return;
		}
		
		if (isSelfClosing(line))
		{
			String tagName = line.substring(0,line.length()-1).trim();
			Attributes attrs = findAttributes(line);
			onElementStart(tagPath.getPath(), tagName, attrs);
			onElementEnd(tagPath.getPath(), tagName);
		}
	}
	
	private Attributes findAttributes(String line) 
	{
		Attributes attrs = new Attributes();
		String[] tokens = line.split(" ");
		for (int i=1,l=tokens.length; i<l; i++)
		{
			String att = tokens[i];
			String[] t = att.split("=");
			var a = new Attribute();
			if (t.length < 2) return null;
			if (t[0].length() < 1) return null;
			a.setName( t[0] );
			a.setValue( t[1].replace("\"", "") );
			attrs.addAttribute(a);
		}
		return attrs;
	}
	
	private void parseStream(InputStream fis) throws XMLBadPrologException, IOException
	{
		String line = "";
		boolean hasTag = false;
		while (fis.available() > 0) 
		{
			char c = (char)fis.read();
			String s = ""+c;
			
			if (isNewTag(s))
			{
				hasTag = true;
				continue;
			}
			
			if (isEndTag(s))
			if (hasTag)
			{
				line = compact(line);
				if (isProlog(line))
				{
					parseProlog(line);
				}
				else
				{
					parseTag(line);
				}
				
				line = "";
				hasTag = false;
				continue;
			}
			
			if (hasTag) line += s;
			else onCharacter(tagPath.getPath(),c);
		}
	}
	
	private String compact(String line) 
	{
		line = line.replace("\t", " ");
		line = line.trim().replaceAll("\\s{2,}", " ");
		line = line.replace(" =", "=");
		line = line.replace("= ", "=");
		return line;
	}
	
	private boolean isNewTag(String chr)
	{
		return chr.equalsIgnoreCase("<");
	}
	
	private boolean isEndTag(String chr)
	{
		return chr.equalsIgnoreCase(">");
	}
	
	private boolean isSelfClosing(String text)
	{
		return text.endsWith("/");
	}
	
	private boolean isTagOpening(String text)
	{
		return !text.contains("/");
	}
	
	private boolean isTagClosing(String text)
	{
		return text.startsWith("/");
	}
	
	private boolean isProlog(String text)
	{
		return text.contains("?");
	}
}
