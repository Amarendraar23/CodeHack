package org.addict.code;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class Refresh{

	static String startUrl = "https://yts.am/browse-movies/0/all/all/7//rating{pageNo}";
	static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";
	static Set<String> ignoreSet = new HashSet<String>();
	static int downloadSize = 100000;
	static Map<String,String> failedList = new HashMap<String,String>();

	public static void main(String[] args){

		long start = System.currentTimeMillis();
//		downloadSize = Integer.parseInt(args[0]);
		System.out.println("Download Size==="+downloadSize);
		List<String> diskMovieList = diskMoviesList();
		readMovieList(diskMovieList);
		System.out.println("Done Fetching List in :"+(System.currentTimeMillis()-start)/1000+" secs");

	}

	private static List<String> diskMoviesList() {

		try {
			Files.write(Paths.get("download.list"),"".getBytes(),StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		getIgnoreList();
		List<String> directoryList = readDirList();
		List<String> list = new ArrayList<String>();
		for(String dirString : directoryList)
		{
			File file = new File(dirString);
			String[] directories = file.list(new FilenameFilter() {
				public boolean accept(File current, String name) {
					return new File(current, name).isDirectory();
				}
			});

			for(int i=0;i<directories.length;i++)
			{
				if(directories != null)
					list.add(directories[i].replaceAll("\\(\\d+\\)", ""));
			}

		}
		return list;
	}

	private static void getIgnoreList() {
		try {
			ignoreSet = Files.readAllLines(new File("ignore.list").toPath(), Charset.defaultCharset()).stream().collect(Collectors.toSet());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> readDirList() {
		List<String> list = null;
		try {
			list = Files.readAllLines(new File("directory.list").toPath(), Charset.defaultCharset() );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static void readMovieList(List<String> diskMovieList){
		boolean fulfilled = false;
		int currentPageNo = 1;
		Map<String,String> fileDownload = new HashMap<String,String>();
		while(!fulfilled )
		{
			String currentUrl = startUrl;
			System.out.println(currentPageNo);
			if(currentPageNo>1)
				currentUrl = startUrl.replace("{pageNo}", "?page="+String.valueOf(currentPageNo));
			else
				currentUrl = startUrl.replace("{pageNo}", "");
			currentPageNo++;
			Map<String,String> retrieveList = getYifyMovieLink(currentUrl);

			if(!retrieveList.isEmpty())
				fileDownload.putAll(compareDatabase(retrieveList,diskMovieList));
			else
				fulfilled = true;
			if(fileDownload.size()>=downloadSize)
				fulfilled = true;
		}
		fileDownload.entrySet().forEach(x->
		{
			try {
				Files.write(Paths.get("download.list"), (x.getKey()+"\n"+x.getValue()+"\n").getBytes(),StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static Map<String, String> compareDatabase(Map<String, String> retrieveList, List<String> diskMovieList) {
		Map<String,String> finalList = new HashMap<String,String>();
		for(Entry<String,String> each : retrieveList.entrySet())
		{
			boolean found = false;
			for(String curName : diskMovieList)
			{
				//!curName.contains(each.getKey())
				int ratio = FuzzySearch.ratio(curName,each.getKey());
				if(ratio>90)
				{
					found = true;
					break;
				}
			}
			if(!found && checkToIgnore(each.getKey()))
			{
				finalList.put(each.getKey(), each.getValue());
				System.out.println("Found:"+each.getKey());
			}
			else
				System.out.println("Ignoring: "+each.getKey());
		}
		return finalList;
	}

	private static boolean checkToIgnore(String key) {
		if(ignoreSet.contains(key))
			return false;
		else
			return true;
	}

	private static Map<String, String> getYifyMovieLink(String currentUrl){
		Document doc = null;
		Map<String,String> retrieveList =new HashMap<String,String>();
		try {
			doc = Jsoup.connect(currentUrl).userAgent(userAgent).get();

			Elements links = doc.select("a.browse-movie-title"); 

			links.parallelStream().forEach(link ->
			{
				String  yifyLink  = link.attr("href");
				String movieName  = link.text();
				String imdbLink = new Refresh().getLink(yifyLink,"a.icon","imdb");
				if(imdbLink!=null )
				{
					int votesCount = new Refresh().votesCount(imdbLink);
					if(votesCount>=10000){
						System.out.println(movieName);
						retrieveList.put(movieName, yifyLink);
					}
				}
				else {
					System.err.println("Failed to retrieve for :" + movieName);
					failedList.put(movieName, yifyLink);
				}
					
			});
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Throwable e) {
			e.printStackTrace();
		}
		return retrieveList;
	}

	private int votesCount(String imdbLink) {
		Document doc = null;
		try {
			doc = Jsoup.connect(imdbLink).userAgent(userAgent).get();

			Elements links = doc.select("span.small"); 
			for (Element link : links) {
				if(link.attr("itemprop").equalsIgnoreCase("ratingCount"))
					return Integer.parseInt(link.text().replaceAll(",", ""));
			}
		} catch (IOException e) {
			e.getLocalizedMessage();
		}

		return 0;
	}

	private String getLink(String yifyLink, String xPath, String xPected) {
		Document doc = null;
		try {
			doc = Jsoup.connect(yifyLink).userAgent(userAgent).get();

			Elements links = doc.select(xPath);
			for (Element link : links) {
				String localLink = link.attr("href");
				if(localLink.contains(xPected))
					return localLink;
			}
		} catch (IOException e) {
			e.getLocalizedMessage();
		}
		return null;  
	}
}