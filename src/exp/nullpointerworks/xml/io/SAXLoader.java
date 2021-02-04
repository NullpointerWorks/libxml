/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;

import exp.nullpointerworks.xml.XMLParseException;

public interface SAXLoader
{
	void addEventListener(SAXEventListener el);
	void removeEventListener(SAXEventListener el);
	void clearEventListeners();
	void parse(String path) throws IOException, XMLParseException;
	void parse(InputStream is) throws XMLParseException;
}
