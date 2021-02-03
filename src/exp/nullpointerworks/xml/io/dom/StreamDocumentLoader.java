/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.nullpointerworks.util.StringUtil;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.Text;
import exp.nullpointerworks.xml.XMLBadPrologException;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.DOMLoader;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

/**
 * Document Object Model File Loader. This implementation streams the content of a file and then parses to create an document element structure. This method is less resource intensive than the regular DOM loader.
 */
public class StreamDocumentLoader implements DOMLoader
{
	private Document doc;
	
	public StreamDocumentLoader()
	{
		doc = new Document();
	}
	
	public StreamDocumentLoader(Document d)
	{
		this();
		if (d!=null) doc = d;
		setDocument(doc);
	}
	
	@Override
	public void setDocument(Document d)
	{
		if (d!=null) doc = d;
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
		doc = new Document();
		
		try 
		{
			parseProlog(doc, fis);
		}
		catch (IOException e) 
		{
			throw new XMLParseException(e);
		}
		
		try 
		{
			parseContent(doc, fis);
		}
		catch (IOException e) 
		{
			throw new XMLParseException(e);
		}
		
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
	
	private void parseProlog(Document doc, InputStream fis) throws XMLParseException, IOException
	{
		String line = "";
		String chr;
		boolean hasTag = false;
		while (fis.available() > 0) 
		{
			chr = ""+( (char)fis.read() );
			
			if (isNewTag(chr))
			{
				hasTag = true;
				continue;
			}
			if (isEndTag(chr))
			if (hasTag)
			{
				parseProlog(doc, line);
				break;
			}
			line += chr;
		}
	}
	
	private void parseContent(Document doc, InputStream fis) throws XMLParseException, IOException
	{
		String line = "";
		Element root = null;
		String chr;
		while (fis.available() > 0) 
		{
			chr = ""+( (char)fis.read() );
			if (isNewTag(chr))
			{
				if (line.length() > 0) 
				{
					if (root!=null) 
					{
						root.addChild( new Text(line) );
					}
					else
					{
						// error
					}
					line = "";
				}
				continue;
			}
			
			if (isEndTag(chr))
			{
				root = parseTag(root, line);
				line = "";
				continue;
			}
			
			line += chr;
		}
		
		doc.setRootElement(root);
	}
	
	private Element parseTag(Element root, String line) throws XMLParseException 
	{
		line = StringUtil.compact(line);
		if (line.equals("")) return root;
		
		String[] tokens = line.split(" ");
		String elName = tokens[0];
		
		/*
		 * self-closing tags have to children and return the root
		 */
		if (isSelfClosing(elName))
		{
			elName = elName.substring(0, elName.length()-1);
			Element el = makeElement(elName, tokens);
			if (root != null)
			{
				root.addChild(el);
			}
			else
			{
				// error. if there's no root, return the element. it's the new root
				return el;
			}
			
			return root;
		}
		
		/*
		 * starting tags
		 */
		if (isTagOpening(elName))
		{
			Element el = makeElement(elName, tokens);
			if (root != null)
			{
				root.addChild(el);
			}
			else
			{
				// error
			}
			
			return el;
		}
		
		/*
		 * closing tag
		 */
		if (isTagClosing(elName))
		{
			if (root!=null)
			{
				if (root.getParent()!=null)
					return root.getParent();
			}
			else
			{
				// error
			}
			
			return root;
		}
		
		/*
		 * default
		 */
		return root;
	}
	
	private Element makeElement(String elementName, String[] tokens)
	{
		Element el = new Element(elementName);
		for (int i=1,l=tokens.length; i<l; i++)
		{
			String att = tokens[i];
			el.addAttribute( new Attribute().setAttribute(att) );
		}
		return el;
	}
	
	private void parseProlog(Document doc, String line) throws XMLBadPrologException 
	{
		line = StringUtil.compact(line);
		
		if (line.startsWith("?"))
		{
			if (line.startsWith("? ")) throw new XMLBadPrologException(null);
			if (!line.endsWith("?")) throw new XMLBadPrologException(null);
			String[] tokens = line.split(" ");
			String prologType = tokens[0];
			Prolog pr = null;
			
			if (prologType.equalsIgnoreCase("?xml"))
			{
				pr = new XMLProlog();
				for (int i=1,l=tokens.length-1; i<l; i++)
				{
					String att = tokens[i];
					pr.addAttribute( new Attribute().setAttribute(att) );
				}
			}
			
			if (pr!=null)
				doc.setProlog(pr);
		}
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
}
