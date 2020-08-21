/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2020)
 * Use of this library is subject to license terms.
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
