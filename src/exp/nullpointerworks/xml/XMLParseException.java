/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml;

public class XMLParseException extends Exception 
{
	private static final long serialVersionUID = 5828118488967338023L;

	public XMLParseException(Exception e) 
	{
		super(e);
	}
}
