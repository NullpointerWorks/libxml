package exp.nullpointerworks.xml;

import java.util.ArrayList;
import java.util.List;

import com.nullpointerworks.util.pattern.Iterator;

public class Attributes 
{
	private List<Attribute> attr;
	
	public Attributes()
	{
		attr = new ArrayList<Attribute>();
	}
	
	public Attribute getAttribute()
	{
		return null;
	}
	
	public void addAttribute(Attribute att)
	{
		if (att!=null) attr.add(att);
	}

	public Iterator<Attribute> getIterator() 
	{
		return new Iterator<Attribute>(attr);
	}
}
