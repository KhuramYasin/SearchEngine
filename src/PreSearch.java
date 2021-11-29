import java.util.*;
import java.io.File;

//To store the occurrences of words in the files, this class use InvertedIndex functionality and HashMap.
public class PreSearch {
	public Map<Integer,String> root;
    public HashMap<String, HashSet<Integer>> index;
	
	PreSearch(){
		prepareSearch();
	}
	
	//From files, populates the InvertedIndex database.
	public void prepareSearch() {
		root = new HashMap<Integer,String>();
        index = new HashMap<String, HashSet<Integer>>();
		File directory = new File("WebPages");
		File files[] = directory.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			
			String[] words = getWordsFromFile(files[i]);
			root.put(i,files[i].getName());
			buildMap(words,i,files[i].getName());
		}
	}
	
	//Inserts the specified value into the InvertedIndex database.
	public void buildMap(String[] words , int i, String fileName) {
		for(String word:words) {
			word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<Integer>());
            index.get(word).add(i);
		}
		
	}
	
	//The specified term is searched for in the database.
	public ArrayList<String> find(String phrase){
    	ArrayList<String> nameOfFile;
    	try {
    		phrase = phrase.toLowerCase();
    		nameOfFile = new ArrayList<String>();
	        String[] words = phrase.split("\\W+");
	        String hashKey = words[0].toLowerCase();
	        if(index.get(hashKey) == null) {
	        	 System.out.println("unable to locate!!!");
	        	return null;
	        }
	        HashSet<Integer> res = new HashSet<Integer>(index.get(hashKey));	        
	        for(String word: words){
	            res.retainAll(index.get(word));
	        }
	
	        if(res.size()==0) {
	            System.out.println("unable to locate!!!");
	            return null;
	        }
	        for(int num : res){
	        	nameOfFile.add(root.get(num));
	        }
    	}catch(Exception e) {
    		 System.out.println("unable to locate!!!");
    		 System.out.println("Exception located:" + e.getMessage());
    		 return null;
    	}  
    return nameOfFile;
    }	
	
	//Returns clean words from the supplied file
	public static String[] getWordsFromFile(File f) {
		In in = new In(f);
		String content = in.readAll();
		content = content.replaceAll("[^a-zA-Z0-9\\s]", ""); 
		String[] words = content.split(" ");		
		
		return words;
	}
}
