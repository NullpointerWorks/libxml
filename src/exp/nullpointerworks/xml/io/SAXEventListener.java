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
*XML_START_ELEMENT	The name of the XML element that is starting
*XML_CHARS	The value of the XML element
*XML_PREDEF_REF	The value of a predefined reference
*XML_UCS2_REF	The value of a UCS-2 reference
*XML_UNKNOWN_REF	The name of an unknown entity reference
*XML_END_ELEMENT	The name of the XML element that is ending
*/
	void onElementStart();
	
	void onElementCharacters();
	
	void onElementReference();
	
	void onElementUCS2Reference();
	
	void onElementUnknownReference();
	
	void onElementEnd();
	
	// ============================================================
	// 
	// ============================================================
/*	
*XML_ATTR_NAME	The name of the attribute
*XML_ATTR_CHARS	The value of the attribute
*XML_ATTR_PREDEF_REF	The value of a predefined reference
*XML_ATTR_UCS2_REF	The value of a UCS-2 reference
*XML_UNKNOWN_ATTR_REF	The name of an unknown entity reference
*XML_END_ATTR	Indicates the end of the attribute
*/
	
	void onAttributeName();

	void onAttributeCharacters();
	
	void onAttributeReference();
	
	void onAttributeUCS2Reference();
	
	void onAttributeUnknownReference();

	
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
