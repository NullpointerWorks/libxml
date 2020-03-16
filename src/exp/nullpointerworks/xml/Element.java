/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Element implements Content<Element>
{
	private Element parent = null;
	private List<Content<?>> children;
	private List<Attribute> attributes;
	private String name = "";
	
	/**
	 * 
	 */
	public Element()
	{
		children = new ArrayList<Content<?>>();
		attributes = new ArrayList<Attribute>();
	}
	
	/**
	 * 
	 */
	public Element(String name)
	{
		this();
		setName(name);
	}
	
	@Override
	public String parse()
	{
		return "";
	}
	
	/*
	 * ========================================================
	 * booleans
	 * ========================================================
	 */
	
	@Override
	public boolean isEmpty()
	{
		return children.size() < 1;
	}
	
	/**
	 * 
	 */
	public boolean hasName()
	{
		return name.length() > 0;
	}
	
	/**
	 * 
	 */
	public boolean hasElements()
	{
		boolean hasElement = false;
		for (Content<?> a : children)
		{
			if ((a instanceof Element) )
			{
				hasElement = true;
			}
		}
		return hasElement;
	}
	
	/**
	 * 
	 */
	public boolean hasText()
	{
		for (Content<?> a : children)
		{
			if ((a instanceof Text) ) return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	protected boolean contains(Content<?> el)
	{
		if (this.hashCode() == el.hashCode()) return true;
		
		int l = children.size() - 1;
		for (;l>=0; l--)
		{
			Content<?> a =  children.get(l);
			if (a.hashCode() == el.hashCode()) return true;
		}
		
		if (parent != null)
		if (parent.contains( el ))
		{
			return true;
		}
		
		return false;
	}
	
	/*
	 * ========================================================
	 * setter
	 * ========================================================
	 */
	
	@Override
	public Element setParent(Element p)
	{
		parent = p;
		return this;
	}
	
	/**
	 * 
	 */
	public Element setName(String n)
	{
		String nn = n.trim();
		if (nn.length() > 0) name = nn;
		return this;
	}
	
	/**
	 * 
	 */
	public Element setText(String txt)
	{
		int i=0,l=children.size();
		for (;i<l;i++)
		{
			Content<?> c = children.get(i);
			if ((c instanceof Text) )
			{
				((Text)c).setContent(txt);
				return this;
			}
		}
		addChild( new Text(txt) );
		return this;
	}

	/*
	 * ========================================================
	 * adders
	 * ========================================================
	 */
	
	/**
	 * 
	 */
	public Element addAttribute(Attribute at)
	{
		if (at==null) return this;
		for (Attribute a : attributes)
		{
			if (a.hashCode() == at.hashCode()) 
				return this;
		}
		attributes.add(at);
		return this;
	}
	
	/**
	 * 
	 */
	public Element addAttribute(String name, String value) 
	{
		return addAttribute( new Attribute(name,value) );
	}
	
	/**
	 * 
	 */
	public Element addChild(Content<?> el)
	{
		if (!contains(el))
		{
			el.setParent(this);
			children.add(el);
		}
		return this;
	}
	
	/*
	 * ========================================================
	 * getter
	 * ========================================================
	 */
	
	@Override
	public Element getParent()
	{
		return parent;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 */
	public String getStart()
	{
		return "<";
	}
	
	/**
	 * 
	 */
	public String getEnd()
	{
		return ( (children.size() > 0)?"":"/" )+">";
	}
	
	/**
	 * 
	 */
	public Attribute getAttribute(int index)
	{
		if (index >= 0)
		if (index < attributes.size())
		{
			return attributes.get(index);
		}
		return null;
	}

	/**
	 * 
	 */
	public String getAttributeValue(int index)
	{
		if (index >= 0)
		if (index < attributes.size())
		{
			return attributes.get(index).getValue();
		}
		return "";
	}
	
	/**
	 * 
	 */
	public Attribute getAttribute(String name)
	{
		for (Attribute a : attributes)
		{
			if (a.getName().equalsIgnoreCase(name))
			{
				return a;
			}
		}
		return null;
	}

	/**
	 * 
	 */
	public String getAttributeValue(String name)
	{
		for (Attribute a : attributes)
		{
			if (a.getName().equalsIgnoreCase(name))
			{
				return a.getValue();
			}
		}
		return "";
	}
	
	/**
	 * 
	 */
	public List<Attribute> getAttributes()
	{
		return attributes;
	}
	
	/**
	 * 
	 */
	public List<Content<?>> getAbstractChildren()
	{
		return children;
	}
	
	/**
	 * 
	 */
	public List<Content<?>> getAbstractChildren(String name)
	{
		List<Content<?>> ch = new ArrayList<Content<?>>();
		for (Content<?> a : children)
		{
			if (a.getName().equalsIgnoreCase(name))
			{
				ch.add(a);
			}
		}
		return ch;
	}
	
	/**
	 * 
	 */
	public Content<?> getAbstractChild(int index)
	{
		if (index >= 0)
		if (index < children.size())
		{
			return children.get(index);
		}
		return null;
	}

	/**
	 * 
	 */
	public Content<?> getAbstractChild(String name)
	{
		for (Content<?> a : children)
		{
			if (a.getName().equalsIgnoreCase(name))
			{
				return a;
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	public Element getChild(int index)
	{
		if (index >= 0)
		if (index < children.size())
		{
			var el = children.get(index);
			if (el instanceof Element) return (Element)el;
		}
		return null;
	}
	
	/**
	 * 
	 */
	public Element getChild(String name)
	{
		for (Content<?> a : children)
		{
			if (a.getName().equalsIgnoreCase(name))
			{
				if (a instanceof Element) return (Element)a;
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	public List<Element> getChildren()
	{
		List<Element> ch = new ArrayList<Element>();
		for (Content<?> a : children)
		{
			if (a instanceof Element) ch.add((Element)a);
		}
		return ch;
	}
	
	/**
	 * 
	 */
	public List<Element> getChildren(String name)
	{
		List<Element> ch = new ArrayList<Element>();
		for (Content<?> a : children)
		{
			if (a.getName().equalsIgnoreCase(name))
				if (a instanceof Element) 
					ch.add((Element)a);
		}
		return ch;
	}
	
	/**
	 * 
	 */
	public String getText()
	{
		for (Content<?> a : children)
		{
			if ((a instanceof Text) ) return ((Text)a).getContent();
		}
		return null;
	}
	
	/**
	 * 
	 */
	public String getText(int index)
	{
		int l = 0;
		for (Content<?> a : children)
		{
			if ((a instanceof Text) )
			{
				if (l==index) return ((Text)a).getContent();
				l++;
			}
		}
		return null;
	}

	/* 
	 * ========================================================
	 * removal
	 * ========================================================
	 */
	
	/**
	 * 
	 */
	public Element remAttribute(Attribute at)
	{
		int l = attributes.size() - 1;
		for (;l>=0; l--)
		{
			Attribute a =  attributes.get(l);
			if (a.hashCode() == at.hashCode()) 
			{
				attributes.remove(l);
				return this;
			}
		}
		return this;
	}

	/**
	 * 
	 */
	public Element remChild(Content<?> at)
	{
		int l = children.size() - 1;
		for (;l>=0; l--)
		{
			Content<?> a =  children.get(l);
			if (a.hashCode() == at.hashCode()) 
			{
				children.remove(l);
				return this;
			}
		}
		return this;
	}
	
	/**
	 * 
	 */
	public Element remAll()
	{
		children.clear();
		attributes.clear();
		return this;
	}
}
