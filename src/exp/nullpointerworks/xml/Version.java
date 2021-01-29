/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
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
