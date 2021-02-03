package exp.nullpointerworks.xml.io.sax;

import java.util.ArrayList;
import java.util.List;

import com.nullpointerworks.util.pattern.Iterator;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Attributes;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.prolog.Prolog;
import exp.nullpointerworks.xml.prolog.XMLProlog;

public class SAXDocumentBuilder implements SAXEventListener
{
	private Document document = null;
	private Element current = null;
	private List<Element> path;
	
	public SAXDocumentBuilder()
	{
		path = new ArrayList<Element>();
	}
	
	public Document getDocument() 
	{
		return document;
	}
	
	@Override
	public void onDocumentStart() 
	{
		document = new Document();
		current = new Element("xml");
		path.add(current);
	}
	
	@Override
	public void onDocumentEnd() 
	{
		Element root = path.get(0).getChild(0);
		
		
		document.setRootElement(root);
	}
	
	@Override
	public void onDocumentProlog(Attributes attrs) 
	{
		Prolog pr = new XMLProlog();
		Iterator<Attribute> it = attrs.getIterator();
		while (it.hasNext())
		{
			Attribute a = it.getNext();
			pr.addAttribute(a);
		}
		document.setProlog(pr);
	}

	@Override
	public void onElementStart(String xmlPath, String eName, Attributes attrs) 
	{
		Element el = new Element(eName);
		Iterator<Attribute> it = attrs.getIterator();
		while (it.hasNext())
		{
			Attribute a = it.getNext();
			el.addAttribute(a);
		}
		current.addChild(el);
		current = el;
		path.add(el);
	}
	
	@Override
	public void onElementEnd(String xmlPath, String eName) 
	{
		path.remove( path.size()-1 );
		current = path.get( path.size()-1 );
	}
	
	@Override
	public void onCharacter(String xmlPath, char c)
	{
		String s = ""+c;
		if (s.equals("\t")) return;
		if (s.equals("\r")) return;
		if (s.equals("\n")) return;
		current.setText( current.getText() + s );
	}
}
