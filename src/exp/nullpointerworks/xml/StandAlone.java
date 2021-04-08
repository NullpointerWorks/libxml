/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml;

public enum StandAlone
{
	YES("yes"),
	NO("no");
	private final String txt;
	private StandAlone(String t) {txt = t;}
	public String asString() {return txt;}
}
