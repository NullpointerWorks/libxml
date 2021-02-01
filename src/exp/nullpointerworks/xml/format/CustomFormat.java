/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.format;

public class CustomFormat implements Format 
{
	private String space=" ",tab="",newline="";
	
	public CustomFormat() {}
	
	public CustomFormat(Format f)
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
	
	public void setSpace(String string) 
	{
		space = string;
	}
	
	public void setTab(String string) 
	{
		tab = string;
	}
	
	public void setNewLine(String string) 
	{
		newline = string;
	}
}
