import java.util.*;
import java.util.Map.Entry;


public class SearchSimillarWords {
	//Creates a HashMap representing the distance between two words in the PreSearch database.
	private static HashMap<String, Integer> editDistanceSorting(String key, PreSearch preSearch) {
		Iterator <Entry<String, HashSet<Integer>>> iterator = preSearch.index.entrySet().iterator();
		HashMap <String, Integer> iDistance = new HashMap <String, Integer>();

		while(iterator.hasNext())   
		{  			
			Map.Entry<String, HashSet<Integer>> mapEntry = (Map.Entry<String, HashSet<Integer>>)iterator.next();
			String str = mapEntry.getKey().toString();			
			int distance = Sequences.editDistance(str, key);
			if(distance < 3 && distance != 0) {
				iDistance.put(str, distance);				
			}
		}
		
		HashMap<String, Integer> sDistance = SortResultsByRank.sortValues(iDistance);
		return sDistance;
	}
	
	//Searches the database for terms that are similar.
	public static void searchSimillar(String key, int numberofResults,PreSearch preSearch) {		
		HashMap<String,Integer> sDistance = editDistanceSorting(key,preSearch);
		Iterator<Entry<String, Integer>> iterator = sDistance.entrySet().iterator();  
		while(iterator.hasNext())   
		{  			
			Map.Entry<String, Integer> mapEntry = (Map.Entry<String, Integer>)iterator.next();
			String str = mapEntry.getKey().toString();			
			System.out.println("instead of displaying results for : "+str);
			
			//Looking for the term with the shortest distance.
			Map<String,Integer> sortedMap;
			ArrayList<String> as = preSearch.find(str);
			String phrase = str.toLowerCase();
			
			sortedMap = SortResultsByRank.orderByRank(as, phrase);			
			Search.ShowResult(sortedMap,numberofResults);
			break;
		}
		
	}
}
