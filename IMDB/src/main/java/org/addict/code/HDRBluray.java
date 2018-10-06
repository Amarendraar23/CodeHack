package org.addict.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HDRBluray {

	private static WebClient webClient;
	 
	static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";
	public static void main(String[] args) {
		List<String> moviesList = new ArrayList<String>();
		boolean flag = true;
		int i=0;
		while(flag) {
			String url = "";
			if(i==0)
				url = "http://www.blu-ray.com/movies/search.php?action=search&ultrahd=1&sortby=releasetimestamp";
			else
				url = "http://www.blu-ray.com/movies/search.php?action=search&ultrahd=1&sortby=releasetimestamp&page="+i;
			find4kmovies(url,moviesList);
			flag =false;
			i++;
		}
	}

	private static void find4kmovies(String url, List<String> moviesList) {
		webClient = new WebClient();
		 webClient.getOptions().setCssEnabled(true);
		    webClient.getOptions().setJavaScriptEnabled(true);
		 
		    try {
				HtmlPage page = webClient.getPage(url);
				String xpath = "/html/body/center[2]/table/tbody/tr/td[4]/table[5]/tbody/tr/td[2]/a";
				HtmlAnchor latestPostLink = (HtmlAnchor) page.getByXPath(xpath).get(0);
				System.out.println(latestPostLink.getTextContent());
			} catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
