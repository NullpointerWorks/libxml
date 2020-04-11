/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import exp.nullpointerworks.xml.Document;

public class DocumentBuilder
{	
	/*
	 * returns DOM loaders
	 */
	public static IDocumentLoader getDOMLoader()
	{
		return new DOMLoader(null);
	}
	
	public static IDocumentLoader getDOMLoader(Document doc)
	{
		return new DOMLoader(doc);
	}
	
	/*
	 * returns SAX loaders
	 */
	public static IDocumentLoader getSAXLoader()
	{
		return new SAXLoader(null);
	}
	
	public static IDocumentLoader getSAXLoader(Document doc)
	{
		return new SAXLoader(doc);
	}
	
}
