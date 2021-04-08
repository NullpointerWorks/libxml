/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
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
	void onCharacter(String xmlPath, String c);
}
