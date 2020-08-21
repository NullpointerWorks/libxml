/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2020)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.format;

final class AbstractFormat implements Format 
{
	private String space="",tab="",newline="";
	
	public AbstractFormat() {}
	
	AbstractFormat(Format f)
	{
		space = f.getSpace();
		tab = f.getTab();
		newline = f.getNewLine();
	}
	
	@Override
	public String getSpace() 
	{
		return space;
	}
	
	@Override
	public String getTab() 
	{
		return tab;
	}
	
	@Override
	public String getNewLine() 
	{
		return newline;
	}
	
	protected void setSpace(String string) 
	{
		space = string;
	}
	
	protected void setTab(String string) 
	{
		tab = string;
	}
	
	protected void setNewLine(String string) 
	{
		newline = string;
	}
}
