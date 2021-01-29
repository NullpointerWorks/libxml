/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.prolog;

import java.util.List;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Encoding;
import exp.nullpointerworks.xml.StandAlone;
import exp.nullpointerworks.xml.Version;

public class XMLProlog extends AbstractProlog
{
	public XMLProlog()
	{
		super();
	}
	
	public XMLProlog(Version v, Encoding e)
	{
		super();
		addAttribute( new Attribute().setName("version").setValue( v.asString()) );
		addAttribute( new Attribute().setName("encoding").setValue( e.asString()) );
	}
	
	public XMLProlog(Version v, Encoding e, StandAlone s)
	{
		this(v,e);
		//addAttribute( new Attribute().setName("version").setValue(v.asString()) );
		//addAttribute( new Attribute().setName("encoding").setValue(e.asString()) );
		addAttribute( new Attribute().setName("standalone").setValue( s.asString() ) );
	}
	
	@Override
	public String getString()
	{
		List<Attribute> attributes = getAttributes();
		String add = " ";
		for (Attribute a : attributes)
		{
			String val = a.getValue();
			if (val.startsWith("\"") && val.endsWith("\""))
			{
				add += a.getName()+"="+a.getValue()+" ";
			}
			else
			{
				add += a.getName()+"=\""+a.getValue()+"\" ";
			}
		}
		return "<?xml"+add+"?>";
	}
}
