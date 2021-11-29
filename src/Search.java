import java.util.Map.Entry;
import java.io.File;
import java.util.*;

public class Search {
	//Searches for PreSearch.java created phrase.
	public static void phraseSearch(String str,int numResults)
    {
        String phrase = str.toLowerCase();
        PreSearch preSearch = new PreSearch();
        ArrayList<String> list = preSearch.find(phrase);

        if(list != null) {			
            Map<String,Integer> sortedMap = SortResultsByRank.orderByRank(list, phrase);
            ShowResult(sortedMap,numResults);
        }
        else {		
            SearchSimillarWords.searchSimillar(phrase,numResults,preSearch);
        }
    }
	
	//Prints given number of results from the provided Map.
	public static void ShowResult(Map <String,Integer> map, int numResults) {
		Iterator<Entry<String, Integer>> iterator = map.entrySet().iterator();

		for(int i = 0; iterator.hasNext() && numResults > i; i++)   
		{  			
			Map.Entry<String, Integer> mapEntry = (Map.Entry<String, Integer>)iterator.next();
			String file = mapEntry.getKey().toString();			
			File f = new File("WebPages/" + file);

			In in = new In(f);
			String url = in.readLine();

			System.out.println("-----------------------------------------");
			System.out.println(file.substring(0, file.length() - 4) + "\t\t\tOccuruces : "+mapEntry.getValue());
			System.out.println(url);  
		}
	}
}
