/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2020)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml;

public class Attribute
{
	private String name = "";
	private String value = "";
	
	public Attribute() {}
	
	public Attribute(String n)
	{
		setName(n);
	}
	
	public Attribute(String n, String v)
	{
		setName(n);
		setValue(v);
	}
	
	public Attribute setAttribute(String att)
	{
		String[] t = att.split("=");
		if (t.length < 2) return null;
		if (t[0].length() < 1) return null;
		setName(t[0]);
		setValue(t[1]);
		return this;
	}
	
	public Attribute setName(String n)
	{
		if (n.equalsIgnoreCase("")) return null;
		name = ""+n;
		return this;
	}

	public Attribute setValue(String v)
	{
		value = ""+v;
		return this;
	}
	
	public String getName()
	{
		return name;
	}

	public String getValue()
	{
		return value;
	}

	public String getString()
	{
		return getName()+"="+getValue();
	}

	public boolean hasValue()
	{
		return !value.equalsIgnoreCase("");
	}
}
