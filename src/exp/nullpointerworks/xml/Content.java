/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml;

/**
 * 
 */
public interface Content<C>
{
	public String parse();
	
	/**
	 * 
	 */
	public String getName();
	
	/**
	 * 
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 */
	public Element getParent();
	
	/**
	 * 
	 */
	public C setParent(Element element);
}
