/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2020)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.prolog;

import exp.nullpointerworks.xml.Attribute;

public interface Prolog
{
	public Prolog addAttribute(Attribute att);
	public String getString();
	public String getEncoding();
}
