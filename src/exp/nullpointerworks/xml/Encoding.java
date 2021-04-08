/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml;

public enum Encoding 
{
	/**
	 * UTF-8 encoding flag.
	 * @since 1.0.0
	 */
	UTF8("UTF-8"),
	
	/**
	 * UTF-16 encoding flag.
	 * @since 1.0.0
	 */
	UTF16("UTF-16"),
	
	/**
	 * UTF-32 encoding flag.
	 * @since 1.0.0
	 */
	UTF32("UTF-32");
	
	private final String txt;
	private Encoding(String t) {txt = t;}
	public String asString() {return txt;}
}
