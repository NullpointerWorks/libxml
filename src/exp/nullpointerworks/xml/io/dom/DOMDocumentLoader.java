/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.nullpointerworks.util.StringUtil;
import com.nullpointerworks.util.file.textfile.TextFile;
import com.nullpointerworks.util.file.textfile.TextFileParser;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.Text;
import exp.nullpointerworks.xml.XMLBadPrologException;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.io.DocumentLoader;
import exp.nullpointerworks.xml.io.XMLLoaderType;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

/**
 * Document Object Model File Loader. Loads the entire file into memory and then parses to create an document element structure. The primary disadvantage is that it's resource intensive when parsing large files.
 */
public class DOMDocumentLoader implements DocumentLoader
{
	private Document doc;

	public DOMDocumentLoader()
	{
		doc = new Document();
	}
	
	public DOMDocumentLoader(Document doc)
	{
		if (doc==null) doc = new Document();
		this.doc=doc;
	}
	
	@Override
	public XMLLoaderType getLoaderType() 
	{
		return XMLLoaderType.DOM;
	}
	
	@Override
	public Document getDocument()
	{
		return doc;
	}
	
	@Override
	public Document parse(String path) throws FileNotFoundException, XMLParseException
	{
		File initialFile = new File(path);
	    InputStream stream;
	    stream = new FileInputStream(initialFile);
		return parse(stream);
	}
	
	@Override
	public Document parse(InputStream is) throws XMLParseException
	{
		TextFile tf = TextFileParser.stream(is);
		Document doc = toDocument(tf);
		tf.clear();
		return doc;
	}
	
	/*
	 * =========================================
	 * reading code
	 * =========================================
	 */
	
	/**
	 * @throws XMLElementParseException 
	 */
	private Document toDocument(TextFile tf) throws XMLParseException
	{
		String[] lines = tf.getLines();
		String documentLine = "";
		for (String line : lines)
		{
			line = line.trim();
			documentLine += line;
		}
		
		String[] characters = documentLine.split("");
		int i=0,l=characters.length;
		String line = "";
		
		/*
		 * construct prolog tag
		 */
		boolean hasTag = false;
		for (;i<l;i++)
		{
			String chr = characters[i];
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
		
		/*
		 * construct xml data
		 */
		line = "";
		Element root = null;
		for (;i<l;i++)
		{
			String chr = characters[i];
			
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
		return doc;
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
			//Log.out("new closing tag:   "+elName);
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
			//Log.out("new tag:           "+elName);
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
			//Log.out("closing tag:       "+elName);
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
	
	protected boolean isNewTag(String chr)
	{
		return chr.equalsIgnoreCase("<");
	}
	
	protected boolean isEndTag(String chr)
	{
		return chr.equalsIgnoreCase(">");
	}
	
	protected boolean isSelfClosing(String text)
	{
		return text.endsWith("/");
	}
	
	protected boolean isTagOpening(String text)
	{
		return !text.contains("/");
	}
	
	protected boolean isTagClosing(String text)
	{
		return text.startsWith("/");
	}
}
