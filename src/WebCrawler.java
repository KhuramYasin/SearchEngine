import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLConnection;
import java.io.IOException;
import java.util.Scanner;
import java.net.URL;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

//Jsoup is used to crawl the web and produce text files with parsed information.
public class WebCrawler {
	public static String crawl(String link) {
		
		
		String htmlData = urlToHTML(link);
		
		Document docmnt = Jsoup.parse(htmlData);
		String text = docmnt.text();
		String tag = docmnt.title();
		//System.out.print(text);
		makeAFile(tag,text,link);
		
		Elements elmnt = docmnt.select("a");
		String links = "";
		
		for(Element elmnt2 : elmnt) {
			String href = elmnt2.attr("abs:href");
			if(href.length()>3)
			{
				links = links+"\n"+href;
			}
		}
		return links;
	}
	public static void makeAFile(String tag,String text,String link) {
		try {
			String[] tagsplit = tag.split("\\|");
			String newtag = "";
			for(String s : tagsplit) {
				newtag = newtag+" "+s;
			}
			File fl = new File("WebPages//"+newtag+".txt");
			fl.createNewFile();			
			PrintWriter pwtr = new PrintWriter(fl);
			pwtr.println(link);
			pwtr.println(text);
			pwtr.close();
			
		} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public static String urlToHTML(String link){
		try {
			URL url = new URL(link);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			Scanner sc = new Scanner(conn.getInputStream());
			StringBuffer sb = new StringBuffer();
			while(sc.hasNext()) {
				sb.append(" "+sc.next());
			}
			
			String result = sb.toString();
			sc.close();
			return result;
		}
		catch(IOException e) {System.out.println(e);} 
		return link;
	}
	
	public static void pagesCrawel(String links) {
		
		try {
			File f = new File("CrawledPages.txt");
			f.createNewFile();
			FileWriter fwt = new FileWriter(f);
			fwt.close();
						
			String links2 = "";
			for(String link: links.split("\n")) {				
				links2 = links2 + crawl(link);
				System.out.println(link);				
				FileWriter fw = new FileWriter(f,true);
				fw.write(link + "\n");
				fw.close();
			}
			
			String links3 = "";
			for(String link: links2.split("\n")) {
				In in = new In(f);
				String linksRead = in.readAll();
				if(!linksRead.contains(link)) {
					links3 = links3 + crawl(link);
					System.out.println(link);
					FileWriter fw = new FileWriter(f,true);
					fw.write(link + "\n");
					fw.close();
				}
				//System.out.println(link);				
				
			}
			
			for(String link: links3.split("\n")) {
				In in = new In(f);
				String linksRead = in.readAll();
				if(!linksRead.contains(link)) {
					crawl(link);
					System.out.println(link);
					FileWriter fw = new FileWriter(f,true);
					fw.write(link + "\n");
					fw.close();
				}
				//System.out.println(link);				
				
			}
		
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public static void defaultCrawel() {
		String links="https://www.cbc.ca/"+"\n"+"https://www.bbc.com/news/world/us_and_canada"+"\n"+"https://www.ctvnews.ca/"+"\n"+"https://www.cicnews.com/";
		pagesCrawel(links);
	}
	public static void crawlCustom(String line) {
		String[] links = line.split(" ");
		String newLine = "";
		for(String link : links) {
			newLine = newLine + link + "\n";
		}
		pagesCrawel(newLine);
	}
	
	public static void deleteWebPages() {
		File directory = new File("WebPages");
		File files[] = directory.listFiles();
		for (File f : files) {
			f.delete();
		}
		System.out.println("Webpages were deleted!");
	}
	
	public static void main(String[] args) {
		defaultCrawel();
	}
}