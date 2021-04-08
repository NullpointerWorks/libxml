/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml;

public class XMLBadPrologException extends XMLParseException 
{
	private static final long serialVersionUID = -2282218628967458418L;
	
	public XMLBadPrologException(Exception e) 
	{
		super(e);
	}
	public XMLBadPrologException(String msg) 
	{
		super(msg);
	}
}
