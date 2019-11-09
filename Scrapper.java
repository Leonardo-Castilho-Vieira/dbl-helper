package com.db.legends;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.db.legends.model.Constants;
import com.db.legends.model.DBLCharacter;
import com.db.legends.model.Tag;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

public class Scrapper {
	
	private static WebClient webClientRandom() {
		BrowserVersion bv =  BrowserVersion.CHROME;
		
		double ranVal = Math.random();
		
		if(ranVal <= 0.3)
			bv =  BrowserVersion.FIREFOX_17;
		else if(ranVal <= 0.6)
			bv =  BrowserVersion.INTERNET_EXPLORER_10;
			
			
		WebClient cliente = new WebClient(bv);
		cliente.getOptions().setJavaScriptEnabled(false);
		cliente.getOptions().setCssEnabled(false);
		cliente.getOptions().setUseInsecureSSL(true);
		cliente.getOptions().setThrowExceptionOnFailingStatusCode(false);
		
		return cliente;
	}

	public static void main(String[] args) {

		int actualCharId = 1;
		try {
			do {
				WebClient cliente = webClientRandom();
//				HtmlPage page = cliente.getPage(Constants.BASEURL + Constants.CHARSURL + "/" + actualCharId);
				HtmlPage page = cliente.getPage("https://legends.dbz.space/characters/219");
				DBLCharacter character = new DBLCharacter();
				
				character.setCharId(actualCharId);
				fillCharInfo(character, page);
//			System.out.println(page.asXml());
				List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//a[@class='tag']");
				
				List<HtmlSpan> stars =  (List<HtmlSpan>) page.getByXPath("//span[@class=\"star ns\"][@key=\"6\"]");
				
				
				HtmlSpan star7 = stars.get(0);
				
				/* Stars TAG: //span[@class="star ns"][@key="6"] 
				 * 
				 * document.getElementsByClassName("star ns")[5].click();
				 * */
				
				if(!items.isEmpty()) {
					List<Tag> tags = new ArrayList<>();
					for (HtmlElement htmlElement : items) {
						Tag tag = new Tag();
						tag.setTagName(htmlElement.getFirstChild().asText());
						tag.setTagId(getTagId(htmlElement));
						tags.add(tag);
						character.setTags(tags);
					}
					System.out.println(character);
				}
				actualCharId++;
				
				try
				{
				    Thread.sleep(20000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				
			} while(actualCharId <=  Constants.totalCharsAvailable);
			
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
	
	static void fillCharInfo(DBLCharacter character, HtmlPage page) {
		character.setCharName(getCharName(page));
	}
	
	private static String getCharName(HtmlPage page) {
		
		List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//div[@class='head name large img_back']");
		if(!items.isEmpty()) {
			return (items.get(0).asText());
		}
	
		return "Char name not found!";
	}

	static long getTagId(HtmlElement htmlElement) {
		String value = htmlElement.getAttributesMap().get("href").getValue();
		
		long tagId = Long.valueOf(value.split("/")[3]);
		
		return tagId;
	}
}
