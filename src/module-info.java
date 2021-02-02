/**
 * Creative Commons - Attribution, Share Alike 4.0<br>
 * Nullpointer Works (2021)<br>
 * Using this library makes you subject to the license terms.<br>
 * @version 0.15.0 experimental
 * @author Michiel Drost - Nullpointer Works
 */
module libnpw.xml
{
	requires transitive libnpw.util;
	
	exports exp.nullpointerworks.xml;
	exports exp.nullpointerworks.xml.format;
	exports exp.nullpointerworks.xml.io;
	exports exp.nullpointerworks.xml.io.dom;
	exports exp.nullpointerworks.xml.io.sax;
	exports exp.nullpointerworks.xml.prolog;
}
