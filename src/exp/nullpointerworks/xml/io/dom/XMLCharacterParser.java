package exp.nullpointerworks.xml.io.dom;

import com.nullpointerworks.util.StringUtil;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.XMLBadPrologException;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

abstract class XMLCharacterParser 
{
	private String line = "";
	private boolean hasTag = false;
	private boolean hasProlog = false;
	private Element root = null;
	
	public void init()
	{
		line = "";
		hasTag = false;
		hasProlog = false;
		root = null;
	}
	
	public void nextCharacter(Document doc, String chr) throws XMLParseException
	{
		if (isNewTag(chr))
		{
			
			return;
		}
		
		if (isEndTag(chr))
		{
			
			
			
			return;
		}
		
		line += chr;
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
