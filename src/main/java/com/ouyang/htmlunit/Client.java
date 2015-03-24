package com.ouyang.htmlunit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ouyang.util.StringUtil;

public class Client {
	private static Logger logger = Logger.getLogger(Client.class);
	public static void main(String[] args) {
		String next = "下一页";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		Map<String, HtmlAnchor> heroDetail = new HashMap<>();
		try {
			HtmlPage page = client.getPage("http://db.dota.uuu9.com/hero/list");
			getAllHero(next, heroDetail, page);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		
		for (String key : heroDetail.keySet()) {
			try {
				HtmlPage heroPage = heroDetail.get(key).click();
				String pageString = heroPage.asText();
//				System.out.println(pageString);
				String nameString = getNextLine(pageString, "DOTA饰品");
				System.out.println("name:"+nameString);
				String otherName = getNextLine(pageString, nameString);
				System.out.println("othername:"+otherName);
				String history = getNextLine(pageString, "背景故事：");
				System.out.println("背景故事："+history);
				System.out.println("================================================================");
			} catch (IOException e) {
				logger.error("Get hero detail page error!");
			}
		}
		
		client.closeAllWindows();
	}

	private static String getNextLine(String pageString, String curLine) {
		return StringUtils.substringBetween(pageString, curLine+"\r\n", "\r\n");
	}

	private static void getAllHero(String next, Map<String, HtmlAnchor> heroDetail,
			HtmlPage page) throws IOException {
		List<HtmlAnchor> anchors = page.getAnchors();
		for (HtmlAnchor htmlAnchor : anchors) {
			String text = htmlAnchor.asText();
			String href = htmlAnchor.getHrefAttribute();
			if(href.contains("/hero/show/")&& StringUtil.isNotBlank(text)&&(!heroDetail.containsKey(href))){
				heroDetail.put(href, htmlAnchor);
			}
			if(next.equals(text)){
				HtmlPage nextPage = htmlAnchor.click();
				getAllHero(next, heroDetail, nextPage);
			}
		}
	}
}
