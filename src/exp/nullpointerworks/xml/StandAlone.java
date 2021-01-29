/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
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
