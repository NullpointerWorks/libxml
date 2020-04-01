package exp.nullpointerworks.xml.format;

import exp.nullpointerworks.xml.format.Format;

public class PrettyWindowsFormat implements Format
{
	@Override
	public String getSpace() 
	{
		return " ";
	}
	
	@Override
	public String getTab() 
	{
		return "\u0009";
	}
	
	@Override
	public String getNewLine() 
	{
		return "\r\n";
	}
}