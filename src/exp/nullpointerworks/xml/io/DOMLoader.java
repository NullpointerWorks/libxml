/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;

public interface DOMLoader
{
	void setDocument(Document d);
	Document getDocument();
	Document parse(String path) throws IOException, XMLParseException;
	Document parse(InputStream is) throws XMLParseException;
}
