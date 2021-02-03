/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

import exp.nullpointerworks.xml.Attributes;

/**
 * 
 */
public interface SAXEventListener 
{
	void onDocumentStart();
	void onDocumentEnd();
	void onDocumentProlog(Attributes attrs);
	
	void onElementStart(String xmlPath, String eName, Attributes attrs);
	void onElementEnd(String xmlPath, String eName);
	void onCharacter(String xmlPath, char c);
}
