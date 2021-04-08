/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml;

public class XMLParseException extends Exception 
{
	private static final long serialVersionUID = 5828118488967338023L;

	public XMLParseException(Exception e) 
	{
		super(e);
	}
	
	public XMLParseException(String msg) 
	{
		super(msg);
	}
}
