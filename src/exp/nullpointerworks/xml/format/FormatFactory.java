/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.format;

public final class FormatFactory 
{
	public static FormatFactory New()
	{
		return new FormatFactory();
	}
	
	public static FormatFactory Modify(Format format) 
	{
		return new FormatFactory(format);
	}
	
	public static Format getPrettyFormat() 
	{
		return new PrettyFormat();
	}
	
	public static Format getPrettyWindowsFormat() 
	{
		return new PrettyWindowsFormat();
	}
	
	public static Format getHtmlFormat() 
	{
		return new HtmlFormat();
	}
	
	public static Format getOneLineFormat() 
	{
		return new OneLineFormat();
	}
	
	// ==========================================
	
	private AbstractFormat format;
	
	public FormatFactory()
	{
		format = new AbstractFormat();
	}
	
	FormatFactory(Format f)
	{
		format = new AbstractFormat(f);
	}
	
	public FormatFactory setSpace(String string)
	{
		format.setSpace(string);
		return this;
	}
	
	public FormatFactory setTab(String string)
	{
		format.setTab(string);
		return this;
	}
	
	public FormatFactory setNewLine(String string)
	{
		format.setNewLine(string);
		return this;
	}
	
	public Format build()
	{
		return format;
	}
}
