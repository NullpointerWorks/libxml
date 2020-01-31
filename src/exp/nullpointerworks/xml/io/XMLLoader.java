package exp.nullpointerworks.xml.io;

import java.io.IOException;
import java.io.InputStream;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.XMLParseException;

public interface XMLLoader
{
	Document getDocument();
	Document parse(String path) throws IOException, XMLParseException;
	Document parse(InputStream is) throws XMLParseException;
}