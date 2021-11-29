import java.util.Scanner;



public class Executer {
	//Displays Main menu
	public static void menuOne() {		
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		boolean menu = true;
		while(menu) {
			System.out.println("\n\n-----------------------------------------\nSearch Engine\n-----------------------------------------");
			System.out.println("Press 1 to search for a keyword");
			System.out.println("Press 2 to crawl the pages again (Estimated time for crawl is 15 to 30 minutes)");
			System.out.println("Press 0 to exit");
			System.out.println("-----------------------------------------");
			System.out.print("Choose an option: ");
			String ans = scan.nextLine();
			
			if(ans.equals("1")) {
				System.out.print("Please enter the keyword to search: ");
				Search.phraseSearch(scan2.nextLine(),10);
			} else if(ans.equals("2")){
				menuTwo();
				menu=false;
			} else if(ans.equals("0")){
				System.out.println("Exiting, thank you for visiting us");
				System.exit(0);
			} else {
				System.out.println("Invalid option, Try again.");
			}
		}
		scan.close();
		scan2.close();
		
	}
	//Displays CrwalMenu.
	public static void menuTwo() {
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		boolean menu = true;
		while(menu) {
			System.out.println("\n\n-----------------------------------------\nWeb Crawling\n-----------------------------------------");
			System.out.println("Press 1 to enter websites to crawl");
			System.out.println("Press 2 to crawl the default web pages");
			System.out.println("Press 3 to Erase the webpages crawled");
			System.out.println("Press 0 to exit");
			System.out.println("-----------------------------------------");
			System.out.print("Select an option: ");
			String ans = scan.nextLine();
			
			if(ans.equals("1")) {
				System.out.println("Enter websites to crawl saperated by a whitespace\n");
				WebCrawler.crawlCustom(scan2.nextLine());
			} else if(ans.equals("2")){
				System.out.println("Executing crawl on default links");
				WebCrawler.defaultCrawel();
			} else if(ans.equals("3")){
				System.out.println("Wiping WebPages");
				WebCrawler.deleteWebPages();
			} else if(ans.equals("0")){
				System.out.println("Exiting, thank you for visiting us");
				System.exit(0);
			} else {
				System.out.println("Invalid input");
			}
			
			menu = false;
		}
		System.out.println("Exiting Program.");
		scan.close();
		scan2.close();
	}
	public static void main(String[] args) {
		menuOne();
	}
}
