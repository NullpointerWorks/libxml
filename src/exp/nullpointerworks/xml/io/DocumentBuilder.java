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
	
}
