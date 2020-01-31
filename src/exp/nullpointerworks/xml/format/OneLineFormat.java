package exp.nullpointerworks.xml.format;

public final class OneLineFormat implements Format
{
	@Override
	public String getSpace() 
	{
		return " ";
	}

	@Override
	public String getTab() 
	{
		return "";
	}

	@Override
	public String getNewLine() 
	{
		return "";
	}
}
