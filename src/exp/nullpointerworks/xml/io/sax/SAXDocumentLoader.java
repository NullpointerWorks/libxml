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

import com.nullpointerworks.util.pattern.Iterator;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLBadPrologException;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.XMLLoaderType;

/**
 * Simple API for XML (SAX). Streamed unidirectional loading with events.
 */
public class SAXDocumentLoader implements DocumentLoader, SAXEventListener
{
	private SAXEventListener saxel;
	private Document doc = null;
	private TagPath tagPath;
	
	public SAXDocumentLoader()
	{
		tagPath = new TagPath();
		saxel = this;
	}
	
	public SAXDocumentLoader(SAXEventListener l)
	{
		this();
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
	public Document parse(InputStream fis) throws XMLParseException
	{
		if (saxel == null) saxel = this;
		
		tagPath.push("xml");
		saxel.onDocumentStart();
		try 
		{
			parseStream(saxel, fis);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		saxel.onDocumentEnd();
		
		try 
		{
			fis.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return doc;
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
	public void onDocumentProlog(Attributes attrs) 
	{
		System.out.print("prolog : ");
		Iterator<Attribute> it = attrs.getIterator();
		while (it.hasNext())
		{
			Attribute a = it.getNext();
			System.out.print( a.getName()+"="+a.getValue()+" " );
		}
		System.out.println();
	}

	@Override
	public void onElementStart(String xmlPath, String eName, Attributes attrs) 
	{
		System.out.print(xmlPath+" : "+eName+" ");
		
		Iterator<Attribute> it = attrs.getIterator();
		while (it.hasNext())
		{
			Attribute a = it.getNext();
			System.out.print( a.getName()+"="+a.getValue()+" " );
		}
		
		System.out.println();
	}

	@Override
	public void onElementEnd(String xmlPath, String eName) 
	{
		System.out.println(xmlPath+" : "+eName);
	}

	@Override
	public void onCharacter(char c)
	{
		//String s = ""+c;
		//if (s.equals("\r")) return;
		//if (s.equals("\n")) return;
		//if (s.equals("\t")) return;
		//System.out.println(s);
	}
	
	// ===============================================================================
	
	private void parseProlog(SAXEventListener saxel, String line) throws XMLBadPrologException 
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
			saxel.onDocumentProlog(attrs);
		}
	}
	
	private void parseTag(SAXEventListener saxel, String line) 
	{
		if (isTagOpening(line))
		{
			String tagName = line.split(" ")[0];
			Attributes attrs = findAttributes(line);
			saxel.onElementStart(tagPath.getPath(), tagName, attrs);
			tagPath.push(tagName);
			return;
		}
		
		if (isTagClosing(line))
		{
			String tagName = line.substring(1).trim();
			tagPath.pop();
			saxel.onElementEnd(tagPath.getPath(), tagName);
			return;
		}
		
		if (isSelfClosing(line))
		{
			
			
		}
	}
	
	private Attributes findAttributes(String line) 
	{
		Attributes attrs = new Attributes();
		String[] tokens = line.split(" ");
		for (int i=1,l=tokens.length; i<l; i++)
		{
			String att = tokens[i];
			attrs.addAttribute( new Attribute().setAttribute(att) );
		}
		return attrs;
	}

	private void parseStream(SAXEventListener saxel, InputStream fis) throws XMLBadPrologException, IOException
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
				//System.out.println("|"+line);
				if (isProlog(line))
				{
					parseProlog(saxel, line);
				}
				else
				{
					parseTag(saxel, line);
				}
				
				line = "";
				hasTag = false;
				continue;
			}
			
			if (hasTag) line += s;
			else saxel.onCharacter(c);
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
