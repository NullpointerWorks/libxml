package app;

import java.io.IOException;
import java.util.List;

import com.nullpointerworks.util.file.Encoding;

import exp.nullpointerworks.xml.*;
import exp.nullpointerworks.xml.format.*;
import exp.nullpointerworks.xml.io.*;
import exp.nullpointerworks.xml.prolog.*;

public class Main 
{
	public static void main(String[] args) {new Main();}

	Format format = FormatBuilder.getPrettyFormat();
	
	public Main()
	{
		makeDocument( "src/assets/text.xml" );
		loadDocument( "src/assets/text.xml" );
	}
	
	Document makeDocument(String path) 
	{
		Document doc = new Document();
		doc.setProlog( new XMLProlog(Version.V10, Encoding.UTF8) );
		
		Element root = doc.getRootElement("base");
		Element el = new Element("person");
		root.addChild( el );
		
		el.addAttribute("y","2019");
		el.addAttribute( new Attribute("m","7") );
		el.addAttribute( new Attribute("d","10") );
		
		el.addChild( new Element("name") );
		el.addChild( new Element("age") );
		el.addChild( new Text("Hello! This is text.") );
		
		try
		{
			DocumentIO.write(doc, path, format);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return doc;
	}
	
	void loadDocument(String path)
	{
		Document doc = null;
		XMLLoader dl = DocumentBuilder.getDOMLoader();
		try
		{
			doc = dl.parse(path);
		} 
		catch (XMLParseException | IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		Element root = doc.getRootElement();
		printElement(root);
	}
	
	/*
	 * print out all element names.
	 */
	void printElement(Element root)
	{
		System.out.println(""+root.getName());
		printElement(root, "");
	}
	
	private void printElement(Element root, String tab)
	{
		List<Content<?>> children = root.getChildren();
		for (Content<?> child : children)
		{
			System.out.println(tab+"  "+child.getName());
			if (child instanceof Element)
			{
				printElement((Element)child,tab+"  ");
			}
		}
	}
}
