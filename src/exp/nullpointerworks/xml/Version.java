/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml;

public enum Version
{
	V10("1.0"),
	V11("1.1");
	private final String txt;
	private Version(String t) {txt = t;}
	public String asString() {return txt;}
}
