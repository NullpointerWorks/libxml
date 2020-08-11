/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml.format;

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