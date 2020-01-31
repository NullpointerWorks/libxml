package exp.nullpointerworks.xml.prolog;

import exp.nullpointerworks.xml.Attribute;

public interface Prolog
{
	public Prolog addAttribute(Attribute att);
	public String getString();
	public String getEncoding();
}
