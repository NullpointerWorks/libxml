/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml.format;

public final class FormatBuilder 
{
	public static FormatBuilder New()
	{
		return new FormatBuilder();
	}
	
	public static FormatBuilder Modify(Format format) 
	{
		return new FormatBuilder(format);
	}
	
	public static Format getPrettyFormat() 
	{
		return new PrettyFormat();
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
	
	public FormatBuilder()
	{
		format = new AbstractFormat();
	}
	
	FormatBuilder(Format f)
	{
		format = new AbstractFormat(f);
	}
	
	public FormatBuilder setSpace(String string)
	{
		format.setSpace(string);
		return this;
	}
	
	public FormatBuilder setTab(String string)
	{
		format.setTab(string);
		return this;
	}
	
	public FormatBuilder setNewLine(String string)
	{
		format.setNewLine(string);
		return this;
	}
	
	public Format build()
	{
		return format;
	}
}
