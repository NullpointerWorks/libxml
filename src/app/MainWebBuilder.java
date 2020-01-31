package app;

import java.io.IOException;
import java.io.InputStream;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.StringUtil;

import exp.nullpointerworks.xml.Attribute;
import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.Text;
import exp.nullpointerworks.xml.XMLParseException;
import exp.nullpointerworks.xml.format.*;
import exp.nullpointerworks.xml.io.DOMLoader;
import exp.nullpointerworks.xml.io.DocumentBuilder;
import exp.nullpointerworks.xml.io.DocumentIO;
import exp.nullpointerworks.xml.prolog.*;

@SuppressWarnings("unused")
public class MainWebBuilder 
{
	public static void main(String[] args) {new MainWebBuilder();}
	
	public MainWebBuilder()
	{
		makeWebPage( "src/assets/index.html" );
		//loadDocument( Loader.getResourceAsStream("/assets/text.xml") );
	}
	
	private void makeWebPage(String path) 
	{
		Document doc = new Document();
		doc.setProlog( new HTMLProlog() );
		
		Element root = doc.getRootElement();
		root.setName("html").addAttribute("lang", "nl");
		
		Element head = new Element("head");
		root.addChild( head );
		head.addChild( new Element("title").setText("PBF Zoeker") );
		head.addChild( getMetaName("description", "PBF Zoekmachine - Axtron BV") );
		head.addChild( getMetaName("keywords", "PBF,Zoeker,Zoekmachine,Axtron") );
		head.addChild( getMetaName("author", "NullPointer Works") );
		head.addChild( getMetaName("viewport", "width=device-width, initial-scale=1") );
		head.addChild( new Element("meta").addAttribute("charset", "utf-8") );
		head.addChild( new Element("meta").addAttribute("http-equiv", "Cache-Control").addAttribute("content", "no-cache") );
		head.addChild( new Text("<!-- load cascading style sheets -->") );
		head.addChild( getStyleSheet("libs/font-awesome/4.7.0/css/font-awesome.min.css") );
		head.addChild( getStyleSheet("css/index.css") );
		head.addChild( getStyleSheet("css/search.css") );
		head.addChild( getStyleSheet("css/content.css") );
		head.addChild( new Element("link").addAttribute("rel", "shortcut icon")
										  .addAttribute("href", "favicon.ico")
										  .addAttribute("type", "image/x-icon") );
		head.addChild( new Element("link").addAttribute("rel", "icon")
										  .addAttribute("href", "favicon.ico")
										  .addAttribute("type", "image/x-icon") );
		
		Element body = new Element("body");
		root.addChild( body );
		body.addChild( new Text("<!-- load javascript libraries -->") );
		body.addChild( getJavaScript("js/string.js") );
		body.addChild( getJavaScript("js/index.js") );
		body.addChild( new Text("<!-- PBF search form -->") );
		
		Element formdiv = new Element("div").addAttribute("class", "pbfsearch");
		body.addChild( formdiv );
		{
			Element form = new Element("form").addAttribute("class", "searchform");
			formdiv.addChild( form );
			form.addChild( new Element("input").addAttribute("type", "text")
											   .addAttribute("placeholder", "Zoeken...")
											   .addAttribute("id", "searchif")
											   .addAttribute("oninput", "this.value = numeric(this.value);") );
			form.addChild( new Element("button").addAttribute("type", "button")
												.addAttribute("onclick", "updatePage();")
												.addChild( new Element("i").addAttribute("class", "fa fa-search").addChild(new Text("")) ));
		}
		
		body.addChild( new Text("<!-- available content -->") );
		Element contentdiv = new Element("div").addAttribute("class", "content").addAttribute("id", "content");
		body.addChild( contentdiv );
		{
			Element entry = new Element("div").addAttribute("id", "#error");
			contentdiv.addChild( entry );
			{
				entry.addChild( new Element("i").addAttribute("class", "fa fa-exclamation-triangle").addAttribute("style", "font-size:52px;color:red;") );
				entry.addChild( new Text("De opgegeven samenstelling is niet beschikbaar.") );
			}
			contentdiv.addChild( new Text("<!-- dynamic content -->") );
			

			/*
			
			<!-- dynamic content -->
			<div class="entry" id="#120-03900">
				<button class="download" onclick="viewPDFTab('pdf/120-P03900/120-P03900.pdf');">
					<i class="fa fa-download"></i> Download 120-03900 Productieboek</button><br/><br/>
			</div>
			
			*/ 
			
			contentdiv.addChild( new Text("<!-- comment form -->") );
			Element commentform = new Element("div").addAttribute("id", "commentform").addAttribute("style", "display:none;");
			contentdiv.addChild( commentform );
			{
				commentform.addChild( new Element("br"));
				
				Element form = new Element("form").addAttribute("id", "cmtform")
												  .addAttribute("class", "commentform")
												  .addAttribute("method", "POST")
												  .addAttribute("onsubmit", "return submitComment();");
				commentform.addChild( form );
				form.addChild( new Element("input").addAttribute("id", "cmtname")
												   .addAttribute("type", "text")
												   .addAttribute("name", "n")
												   .addAttribute("placeholder", "Naam...") );
				
				form.addChild( new Element("input").addAttribute("id", "cmttarget")
												   .addAttribute("type", "text")
												   .addAttribute("name", "t")
												   .addAttribute("style", "display:none;") );
				
				form.addChild( new Element("textarea").addAttribute("id", "cmttext")
													  .addAttribute("name", "s")
													  .addAttribute("placeholder", "Opmerking..")
													  .addAttribute("style", "height:200px") );
				
				form.addChild( new Element("button").addAttribute("type", "submit")
													.addChild( new Text("Plaatsen") ) );
			}
		}
		
		Format format = FormatBuilder.getHtmlFormat();
		try {DocumentIO.write(doc, path, format);} 
		catch (IOException e) {e.printStackTrace();}
	}
	
	private Element getJavaScript(String content)
	{
		return new Element("script").addAttribute("type", "text/javascript").addAttribute("src", content).addChild(new Text(""));
	}
	
	private Element getMetaName(String name, String content)
	{
		return new Element("meta").addAttribute("name", name).addAttribute("content", content);
	}
	
	private Element getStyleSheet(String path)
	{
		return new Element("link").addAttribute("rel", "stylesheet").addAttribute("type", "text/css").addAttribute("href", path);
	}
	
	private void loadDocument(InputStream is)
	{
		Document doc = null;
		
		if (is==null)
		{
			return;
		}
		
		try 
		{
			doc = DocumentIO.stream( is );
		} 
		catch (XMLParseException e) 
		{
			e.printStackTrace();
		}
		
		if (doc==null)
		{
			return;
		}
		
		Log.out( ""+doc.getProlog().getString() );
		
		Element root = doc.getRootElement();
		Log.out(""+root.getName());
	}
}
