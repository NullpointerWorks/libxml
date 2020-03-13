/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml;

public class Text implements Content<Text>
{
	private String text = "";
	private Element parent = null;
	
	public Text() {}
	
	public Text(String n)
	{
		setContent(n);
	}

	@Override
	public String parse()
	{
		return "";
	}

	@Override
	public Element getParent()
	{
		return parent;
	}
	
	@Override
	public String getName()
	{
		return "Text";
	}

	@Override
	public boolean isEmpty()
	{
		return text.equalsIgnoreCase("");
	}
	
	@Override
	public Text setParent(Element p)
	{
		parent = p;
		return this;
	}

	public String getContent()
	{
		return text;
	}

	public void setContent(String c)
	{
		text = c;
	}

	public void addContent(String c)
	{
		text = text + c;
	}
}
