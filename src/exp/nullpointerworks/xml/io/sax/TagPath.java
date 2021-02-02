package exp.nullpointerworks.xml.io.sax;

import java.util.ArrayList;
import java.util.List;

class TagPath 
{
	private List<String> path;
	
	public TagPath()
	{
		path = new ArrayList<String>();
	}

	public String getPath() 
	{
		if (path.size()==0)return "";
		String p = path.get(0);
		for (int i=1,l=path.size(); i<l; i++)
		{
			String s = path.get(i);
			p+="."+s;
		}
		return p;
	}
	
	public void clear()
	{
		path.clear();
	}
	
	public void push(String s)
	{
		path.add(s);
	}
	
	public String pop()
	{
		return path.remove(path.size()-1);
	}
}
