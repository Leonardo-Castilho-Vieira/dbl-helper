package com.db.legends;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.function.Consumer;

import org.apache.xalan.xsltc.compiler.sym;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Scrapper {
	
	//https://legends.dbz.space/characters/191
	static final String url = "https://legends.dbz.space";

	public static void main(String[] args) {

		WebClient cliente = new WebClient();
		cliente.getOptions().setJavaScriptEnabled(false);
		cliente.getOptions().setCssEnabled(false);
		cliente.getOptions().setUseInsecureSSL(true);
		
		try {
			HtmlPage page = cliente.getPage(url + "/characters/191");
//			System.out.println(page.asXml());
			List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//a[@class='tag']");
			if(!items.isEmpty()) {
				for (HtmlElement htmlElement : items) {
					String tagName =  htmlElement.getFirstChild().asText();
				}
			}
				
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	long getTagId(HtmlElement htmlElement) {
		String value = htmlElement.getAttributesMap().get("href").getValue();
		
		long tagId = Long.valueOf(value.split("/")[2]);
		
		return tagId;
	}
}
