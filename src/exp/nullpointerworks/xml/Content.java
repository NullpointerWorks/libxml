/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
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
