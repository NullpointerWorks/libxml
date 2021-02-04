/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io.util;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.XMLBadPrologException;
import exp.nullpointerworks.xml.XMLParseException;

public abstract class CharacterParser 
{
	private String line = "";
	private boolean hasTag = false;
	private boolean hasTagContainment = false;
	
	protected abstract void onDocumentStart();
	protected abstract void onDocumentEnd();
	protected abstract void onDocumentProlog(Attributes attrs);
	protected abstract void onElementStart(String eName, Attributes attrs);
	protected abstract void onElementEnd(String eName);
	protected abstract void onCharacter(String c);
	
	protected final void nextCharacter(String chr) throws XMLParseException
	{
		if (isNewTag(chr))
		{
			line = "";
			hasTag = true;
			return;
		}
		
		if (hasTag)
		{
			if (isEndTag(chr))
			{
				String cline = compact(line);
				if (isProlog(cline))
				{
					parseProlog(cline);
				}
				else
				{
					parseTag(cline);
				}
				
				line = "";
				hasTag = false;
				return;
			}
			
			line += chr;
			return;
		}
		
		if (hasTagContainment) onCharacter(chr);
	}
	
	// ====================================================================
	
	private void parseProlog(String line) throws XMLBadPrologException 
	{
		if (line.startsWith("?"))
		{
			if (line.startsWith("? ")) throw new XMLBadPrologException("Prolog type is undefined.");
			if (!line.endsWith("?")) throw new XMLBadPrologException("");
			
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
			onElementStart(tagName, attrs);
			hasTagContainment = true;
			return;
		}
		
		if (isTagClosing(line))
		{
			String tagName = line.substring(1).trim();
			onElementEnd(tagName);
			hasTagContainment = false;
			return;
		}
		
		if (isSelfClosing(line))
		{
			line = line.substring(0,line.length()-1); // remove '/' character
			String tagName = line.split(" ")[0];
			line = line.substring(tagName.length());
			Attributes attrs = findAttributes(line);
			onElementStart(tagName, attrs);
			onElementEnd(tagName);
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
			if (t.length < 2) continue; // TODO catch attribute markers without values
			
			String name = t[0];
			if (name.length() < 1) continue;
			
			String value = t[1];
			Attribute a = new Attribute(name, value);
			
			attrs.addAttribute(a);
		}
		return attrs;
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
		return text.startsWith("?xml");
	}
}
