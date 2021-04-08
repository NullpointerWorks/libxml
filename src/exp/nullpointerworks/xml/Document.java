/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml;

import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

public class Document
{
	private Prolog prolog;
	private Element root = null;

	public Document()
	{
		prolog = new XMLProlog(Version.V10, Encoding.UTF8);
	}
	
	public Document(Element root)
	{
		prolog = new XMLProlog(Version.V10, Encoding.UTF8);
		setRootElement(root);
	}
	
	public Document setProlog(Prolog pr)
	{
		if (pr!=null) prolog = pr;
		return this;
	}
	
	public Document setRootElement(Element r)
	{
		if (r!=null) root = r;
		return this;
	}
	
	public Prolog getProlog()
	{
		return prolog;
	}
	
	public Element getRootElement()
	{
		return root;
	}
	
	public Element getRootElement(String name)
	{
		root = new Element(name);
		return root;
	}
	
	public boolean isEmpty()
	{
		return root == null;
	}
}
