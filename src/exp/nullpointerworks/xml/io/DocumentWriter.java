/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.util.List;

import com.nullpointerworks.util.file.textfile.TextFile;
import com.nullpointerworks.util.file.textfile.TextFileParser;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Content;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.Text;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.format.PrettyFormat;

public class DocumentWriter 
{
	private Format format;
	
	public DocumentWriter()
	{
		this( new PrettyFormat() );
	}
	
	public DocumentWriter(Format format)
	{
		setFormatter(format);
	}
	
	public void setFormatter(Format format)
	{
		this.format = format;
	}
	
	public void write(Document doc, String path) throws IOException 
	{
		// set file encoding
		TextFile tf = new TextFile();
		tf.setEncoding( doc.getProlog().getEncoding().asString() );
		
		// document header
		tf.addLine(doc.getProlog().getString() + format.getNewLine() );
		
		// root element
		printElements(doc.getRootElement(), tf, 0, format);
		
		// write to disc
		TextFileParser.write(path, tf);
	}
	
	/*
	 * =========================================
	 * writing code
	 * =========================================
	 */
	
	private void printElements(Content<?> root, TextFile tf, int iteration, Format format)
	{
		String tab = tabs(iteration, format);
		
		// if its an element with nested content
		if ((root instanceof Element) )
		{
			Element r = (Element)root;
			List<Content<?>> c = r.getAbstractChildren();
			List<Attribute> a = r.getAttributes();
			
			// if the tag has no children tags and just text, behave without tabs and newline
			boolean textOnly = r.hasText() && !r.hasElements(); 
			String postfix = (textOnly)?"":format.getNewLine();
			
			// if tag has children
			if (c.size() > 0)
			{
				// list attributes
				if (a.size() > 0)
				{
					String list = "";
					for (Attribute atr : a)
					{
						list = list + " "+atr.getName()+"=\"" + atr.getValue()+"\"";
					}
					tf.addLine(tab+"<"+r.getName()+list+">"+postfix);
				}
				else
				{
					tf.addLine(tab+"<"+r.getName()+">"+postfix);
				}
				
				// parse content
				for (Content<?> e : c)
				{
					printElements(e,tf,iteration+1,format);
				}
				
				tab = (textOnly)?"":tab;
				tf.addLine(tab+"</"+r.getName()+">"+format.getNewLine());
			}
			// else self-closing tag
			else
			{
				if (a.size() > 0)
				{
					String list = "";
					for (Attribute atr : a)
					{
						list = list + " "+atr.getName()+"=\"" + atr.getValue()+"\"";
					}
					tf.addLine(tab+"<"+r.getName()+list+"/>"+format.getNewLine());
				}
				else
				{
					tf.addLine(tab+"<"+r.getName()+"/>"+format.getNewLine());
				}
			}
		}
		// else its a text object
		else
		if ((root instanceof Text) )
		{
			Text r = (Text)root;
			boolean textOnly = !r.getParent().hasElements();
			String postfix = (textOnly)?"":format.getNewLine();
			tab = (textOnly)?"":tab;
			String txt = r.getContent();
			tf.addLine(tab+txt+postfix);
		}
	}
	
	private String tabs(int iteration, Format format)
	{
		String t = "";
		for (;iteration>0;iteration--) t = t + format.getTab();
		return t;
	}
}
