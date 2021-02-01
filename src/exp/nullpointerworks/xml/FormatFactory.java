/*
 * Creative Commons - Attribution, Share Alike 4.0
 * Nullpointer Works (2021)
 * Use of this library is subject to license terms.
 */
package exp.nullpointerworks.xml;

import exp.nullpointerworks.xml.format.CustomFormat;
import exp.nullpointerworks.xml.format.Format;
import exp.nullpointerworks.xml.format.HtmlFormat;
import exp.nullpointerworks.xml.format.OneLineFormat;
import exp.nullpointerworks.xml.format.PrettyFormat;
import exp.nullpointerworks.xml.format.PrettyWindowsFormat;

public final class FormatFactory 
{
	/**
	 * Unix style new-line. It uses new-line to indicate a new line of text. Adds tabs and spaces for whitespace.
	 * @return a Unix style new-line formatter
	 */
	public static Format getPrettyFormat() 
	{
		return new PrettyFormat();
	}
	
	/**
	 * It uses carriage-return new-line to indicate a new line of text. Adds tabs and spaces for whitespace.
	 * @return a Windows style new-line formatter
	 */
	public static Format getPrettyWindowsFormat() 
	{
		return new PrettyWindowsFormat();
	}
	
	/**
	 * Identical to "PrettyWindowsFormat". It uses carriage-return new-line to indicate a new line of text. Adds tabs and spaces for whitespace.
	 * @return a Windows style formatting used in HTML
	 */
	public static Format getHtmlFormat() 
	{
		return new HtmlFormat();
	}
	
	/**
	 * Returns a formatting which places all code in a single line of text. No addition whitespace or newlines.
	 * @return a one-liner format
	 */
	public static Format getOneLineFormat() 
	{
		return new OneLineFormat();
	}
	
	/**
	 * Returns a modifiable formatting instance. This object is set as a "OneLineFormat" by default.
	 * @return a modifiable formatting instance
	 */
	public static Format getCustomFormat() 
	{
		return new CustomFormat();
	}
}
