/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.prolog;

/**
 * Defaults to HTML5 prolog.
 * @since 1.0.0
 */
public class HTMLProlog extends AbstractProlog
{
	private String additional = "";
	
	public HTMLProlog()
	{
		super();
	}
	
	public HTMLProlog(LegacyHTML add)
	{
		super();
		additional = " "+add.asString();
	}
	
	public HTMLProlog(String add)
	{
		super();
		additional = " "+add;
	}
	
	@Override
	public String getString()
	{
		return "<!DOCTYPE html"+additional+">";
	}
}
