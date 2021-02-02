/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml;

public class XMLBadPrologException extends XMLParseException 
{
	private static final long serialVersionUID = -2282218628967458418L;
	
	public XMLBadPrologException(Exception e) 
	{
		super(e);
	}
}
