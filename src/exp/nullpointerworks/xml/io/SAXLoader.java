/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;

/*
 * TODO
 * Simple API for XML
 * 
 * streamed unidirectional loading with events
 */
public class SAXLoader implements XMLLoader
{
	private Document doc;
	
	public SAXLoader()
	{
		doc = new Document();
	}
	
	public SAXLoader(Document doc)
	{
		if (doc==null) doc = new Document();
		this.doc=doc;
	}
	
	@Override
	public Document getDocument()
	{
		return null;
	}

	@Override
	public Document parse(String path) throws IOException, XMLParseException
	{
		return null;
	}

	@Override
	public Document parse(InputStream is) throws XMLParseException
	{
		return null;
	}
	
	
	
	
}
