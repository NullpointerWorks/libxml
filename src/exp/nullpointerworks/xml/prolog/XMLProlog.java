package exp.nullpointerworks.xml.prolog;

import java.util.ArrayList;
import java.util.List;

import exp.nullpointerworks.xml.Attribute;

public class XMLProlog implements Prolog
{
	private List<Attribute> attributes;
	
	public XMLProlog() 
	{
		attributes = new ArrayList<Attribute>();
		//this(Version.V10, Encoding.UTF8);
	}
	
	public XMLProlog(String v, String e)
	{
		this();
		addAttribute( new Attribute().setName("version").setValue(v) );
		addAttribute( new Attribute().setName("encoding").setValue(e) );
	}
	
	public XMLProlog(String v, String e, String s)
	{
		this();
		addAttribute( new Attribute().setName("version").setValue(v) );
		addAttribute( new Attribute().setName("encoding").setValue(e) );
		addAttribute( new Attribute().setName("standalone").setValue(s) );
	}
	
	@Override
	public String getString()
	{
		String add = " ";
		for (Attribute a : attributes)
		{
			String val = a.getValue();
			if (val.startsWith("\"") && val.endsWith("\""))
			{
				add += a.getName()+"="+a.getValue()+" ";
			}
			else
			{
				add += a.getName()+"=\""+a.getValue()+"\" ";
			}
		}
		return "<?xml"+add+"?>";
	}
	
	@Override
	public String getEncoding()
	{
		for (Attribute a : attributes)
		{
			if (a.getName().equalsIgnoreCase("encoding"))
			{
				return a.getValue();
			}
		}
		return "UTF-8";
	}

	@Override
	public Prolog addAttribute(Attribute att)
	{
		if (att!=null)
			attributes.add(att);
		return this;
	}
}
