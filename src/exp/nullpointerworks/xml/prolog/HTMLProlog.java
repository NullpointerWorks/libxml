package exp.nullpointerworks.xml.prolog;

import java.util.ArrayList;
import java.util.List;

import exp.nullpointerworks.xml.Attribute;

/**
 * Defaults to HTML5 prolog.
 * @since 1.0.0
 */
public class HTMLProlog implements Prolog
{
	private List<Attribute> attributes;
	private String additional = "";
	
	public HTMLProlog() 
	{
		attributes = new ArrayList<Attribute>();
	}
	
	public HTMLProlog(String add)
	{
		this();
		additional = " "+add;
	}
	
	@Override
	public String getString()
	{
		return "<!DOCTYPE html"+additional+">";
	}
	
	@Override
	public String getEncoding()
	{
		return "UTF8";
	}

	@Override
	public Prolog addAttribute(Attribute att) 
	{
		if (att!=null)
			attributes.add(att);
		return this;
	}
}
