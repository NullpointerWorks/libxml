package exp.nullpointerworks.xml.io;

import exp.nullpointerworks.xml.Document;

public class DocumentBuilder
{	
	/*
	 * returns DOM loaders
	 */
	
	public static XMLLoader getDOMLoader()
	{
		return new DOMLoader(null);
	}
	
	public static XMLLoader getDOMLoader(Document doc)
	{
		return new DOMLoader(doc);
	}
	
	/*
	 * returns SAX loaders
	 */
	
}
