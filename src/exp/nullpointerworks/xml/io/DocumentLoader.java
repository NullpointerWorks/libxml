/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;

public interface DocumentLoader
{
	Document getDocument();
	XMLLoaderType getLoaderType();
	Document parse(String path) throws FileNotFoundException, XMLParseException;
	Document parse(InputStream is) throws XMLParseException;
}
