package exp.nullpointerworks.xml;

public class Text implements Content<Text>
{
	private String text = "";
	private Element parent = null;
	
	public Text() {}
	
	public Text(String n)
	{
		setText(n);
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

	public String getText()
	{
		return text;
	}

	public void setText(String c)
	{
		text = c;
	}

	public void addText(String c)
	{
		text = text + c;
	}
}
