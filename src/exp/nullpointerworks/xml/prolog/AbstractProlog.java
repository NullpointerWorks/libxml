/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml.prolog;

import java.util.ArrayList;
import java.util.List;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Encoding;

abstract class AbstractProlog implements Prolog
{
	private List<Attribute> attributes;
	
	public AbstractProlog() 
	{
		attributes = new ArrayList<Attribute>();
	}
	
	protected final List<Attribute> getAttributes()
	{
		return attributes;
	}
	
	@Override
	public Encoding getEncoding()
	{
		for (Attribute a : attributes)
		{
			if (a.getName().equalsIgnoreCase("encoding"))
			{
				String e = a.getValue();
				if (e.equalsIgnoreCase(Encoding.UTF8.asString())) return Encoding.UTF8;
				if (e.equalsIgnoreCase(Encoding.UTF16.asString())) return Encoding.UTF16;
				if (e.equalsIgnoreCase(Encoding.UTF32.asString())) return Encoding.UTF32;
			}
		}
		return Encoding.UTF8;
	}
	
	@Override
	public Prolog addAttribute(Attribute att)
	{
		if (att!=null)
			attributes.add(att);
		return this;
	}
}
