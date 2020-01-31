package exp.nullpointerworks.xml;

import com.nullpointerworks.util.file.Encoding;

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
}
