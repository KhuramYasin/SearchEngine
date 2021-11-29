import java.io.File;
import java.util.*;


public class SortResultsByRank {
	//Sorts values of given HashMap in descending order
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<String,Integer> sortInverseValues(HashMap<String,Integer> map)
	{		
		List l = new LinkedList(map.entrySet());  
		//Custom Comparator  
		Collections.sort(l, new Comparator(){public int compare(Object o1, Object o2){return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());}});  
		//copying the sorted list in HashMap to preserve the iteration order  
		HashMap<String,Integer> sortedMap = new LinkedHashMap<String,Integer>(); 
		Iterator it = l.iterator(); 
		while ( it.hasNext())   
		{  
			Map.Entry<String,Integer> e = (Map.Entry<String,Integer>) it.next();  
			sortedMap.put(e.getKey(), e.getValue());  
		}   
		return sortedMap;  
	}
	//sorts values of given HashMap in ascending order
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String,Integer> sortValues(HashMap<String,Integer> map){	
			
		List l = new LinkedList(map.entrySet());  
		//Custom Comparator  
		Collections.sort(l, new Comparator(){public int compare(Object o1, Object o2){return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());}});  
		//copying the sorted l in HashMap to preserve the iteration order  
		HashMap<String,Integer> sortedMap = new LinkedHashMap<String,Integer>(); 

		Iterator it = l.iterator(); 
		while (it.hasNext()){  
			Map.Entry<String,Integer> e = (Map.Entry<String,Integer>) it.next();  
			sortedMap.put(e.getKey(), e.getValue());  
		}   

		return sortedMap;  
	}
	
	//Sorts search output in Descending Rank. ArrayList contains list of files that have the given phrase.
	public static Map<String,Integer> orderByRank(ArrayList<String> list, String str) {
		HashMap<String,Integer> wordCount = new HashMap<String,Integer>();
		
		for(String fileName : list) {
			String[] arr = PreSearch.getWordsFromFile(new File("WebPages/"+fileName));
			for(String word:arr) {
				if(word.toLowerCase().equals(str.split("\\W+")[0])) {
					if(!wordCount.containsKey(fileName)) {
						wordCount.put(fileName, 1);
					}
					else {
						wordCount.put(fileName, wordCount.get(fileName)+1);					
					}
				}			
			}
		}

		Map<String,Integer> sortedMap = sortInverseValues(wordCount);

		return sortedMap;
	}
}
