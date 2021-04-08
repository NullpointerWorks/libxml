/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml;

public class XMLElementParseException extends XMLParseException 
{
	private static final long serialVersionUID = 2835931874816025581L;

	public XMLElementParseException(Exception e) 
	{
		super(e);
	}
}
