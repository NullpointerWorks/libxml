/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.xml.io;

/**
 * 
 */
public interface SAXEventListener 
{
	// ============================================================
	// Events that trigger at the start of parsing before the first 
	// element has been reached.
	// ============================================================
	
	/**
	 * An event triggered when a document is going to be parsed.
	 */
	void onStartDocument();
	
	/**
	 * The "version" value from the XML declaration
	 */
	void onVersion();
	
	/**
	 * The "encoding" value from the XML declaration
	 */
	void onEncoding();
	
	/**
	 * The "standalone" value from the XML declaration
	 */
	void onStandAlong();
	
	/**
	 * The value of the Document Type Declaration
	 */
	void onDocType();
	
	// ============================================================
	// 
	// ============================================================
	
	/*
	 * The name of the XML element that is starting
	 */
	void onElementStart();
	
	/*
	 * The value of the XML element
	 */
	void onElementCharacters();
	
	/*
	 * The value of a predefined reference
	 */
	void onElementReference();
	
	/*
	 * The value of a UCS-2 reference
	 */
	void onElementUCS2Reference();
	
	/*
	 * The name of an unknown entity reference
	 */
	void onElementUnknownReference();
	
	/*
	 * The name of the XML element that is ending
	 */
	void onElementEnd();
	
	// ============================================================
	// 
	// ============================================================
	
	/*
	 * The name of the attribute
	 */
	void onAttributeName();
	
	/*
	 * The value of the attribute
	 */
	void onAttributeCharacters();
	
	/*
	 * The value of a predefined reference
	 */
	void onAttributeReference();
	
	/*
	 * The value of a UCS-2 reference
	 */
	void onAttributeUCS2Reference();
	
	/*
	 * The name of an unknown entity reference
	 */
	void onAttributeUnknownReference();
	
	/*
	 * Indicates the end of the attribute
	 */
	void onAttributeEnd();
	
	// ============================================================
	// 
	// ============================================================
/*
4. Events related to XML processing instructions
*XML_PI_TARGET	The name of the target
*XML_PI_DATA	The value of the data
*/

	
	// ============================================================
	// 
	// ============================================================
/*
5. Events related to XML CDATA sections
*XML_START_CDATA	The beginning of the CDATA section
*XML_CHARS	The value of the CDATA section
*XML_END_CDATA	The end of the CDATA section
*/

	
	// ============================================================
	// 
	// ============================================================
/*
6. Other events
*XML_COMMENT	The value of the XML comment
*XML_EXCEPTION	Indicates that the parser discovered an error
*XML_END_DOCUMENT	Indicates that parsing has ended
*/
	
	
}
