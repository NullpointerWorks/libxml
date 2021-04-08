/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml.prolog;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Encoding;

public interface Prolog
{
	public Prolog addAttribute(Attribute att);
	public String getString();
	public Encoding getEncoding();
}
