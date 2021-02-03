package exp.nullpointerworks.xml.io.dom;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.XMLBadPrologException;
import exp.nullpointerworks.xml.XMLParseException;

abstract class XMLCharacterParser 
{
	private String line = "";
	private boolean hasTag = false;
	
	public void reset()
	{
		line = "";
		hasTag = false;
	}
	
	abstract void onDocumentStart();
	abstract void onDocumentEnd();
	abstract void onDocumentProlog(Attributes attrs);
	abstract void onElementStart(String eName, Attributes attrs);
	abstract void onElementEnd(String eName);
	abstract void onCharacter(char c);
	
	public void nextCharacter(String chr) throws XMLParseException
	{
		if (isNewTag(chr))
		{
			hasTag = true;
			return;
		}
		
		if (hasTag)
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
			return;
		}
		
		line += chr;
	}
	
	// ====================================================================
	
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
			onElementStart(tagName, attrs);
			return;
		}
		
		if (isTagClosing(line))
		{
			String tagName = line.substring(1).trim();
			onElementEnd(tagName);
			return;
		}
		
		if (isSelfClosing(line))
		{
			String tagName = line.substring(0,line.length()-1).trim();
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
			if (t.length < 2) return null;
			if (t[0].length() < 1) return null;
			Attribute a = new Attribute(t[0], t[1]);
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
