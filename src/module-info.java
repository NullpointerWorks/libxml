/**
 * This is free and unencumbered software released into the public domain.<br>
 * (http://unlicense.org/)<br>
 * Nullpointer Works (2021)<br>
 *
 * @version 0.16.0 experimental
 * @author Michiel Drost - Nullpointer Works
 */
module libnpw.xml
{
	requires transitive libnpw.util;
	
	exports exp.nullpointerworks.xml;
	exports exp.nullpointerworks.xml.format;
	exports exp.nullpointerworks.xml.io;
	exports exp.nullpointerworks.xml.prolog;
}
