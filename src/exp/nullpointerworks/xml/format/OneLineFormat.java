/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
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
