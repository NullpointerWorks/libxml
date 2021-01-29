/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.prolog;

public enum LegacyHTML
{
	/**
	 * HTML 4.01 Strict
	 */
	HTML401_STRICT("PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\""),
	
	/**
	 * HTML 4.01 Transitional
	 */
	HTML401_TRANSITIONAL("PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\""),
	
	/**
	 * HTML 4.01 Frameset
	 */
	HTML401_FRAMESET("PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\""),
	
	/**
	 * XHTML 1.0 Strict
	 */
	XHTML10_STRICT("PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\""),

	/**
	 * XHTML 1.0 Transitional
	 */
	XHTML10_TRANSITIONAL("PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\""),
	
	/**
	 * XHTML 1.0 Frameset
	 */
	XHTML10_FRAMESET("PUBLIC \"-//W3C//DTD XHTML 1.0 Frameset//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\""),
	
	/**
	 * XHTML 1.1
	 */
	XHTML11("PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\"");
	
	private final String txt;
	private LegacyHTML(String t) {txt = t;}
	public String asString() {return txt;}
}
