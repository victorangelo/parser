package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class counts all the words in a given file, returning the word frequency as output.
 * 
 * @author victorangh
 *
 */
public class FreqParser {

	public static void main(String[] args) throws FileNotFoundException {    
		/**	inserting the right file path here	 */
		File file = new File("/Users/victorangh/Documents/workspace/FreqParser/resources/wordsFile.txt");//put your file here
		Scanner scanFile = new Scanner(new FileReader(file));
		Pattern pattern=Pattern.compile("[^\\w+]");
		scanFile.useDelimiter(pattern);
		ArrayList<String> words = new ArrayList<String>();
		String theWord;
		
		while (scanFile.hasNext())
		{   
		    theWord = scanFile.next();
		    if(!theWord.isEmpty()){
		    	words.add(theWord);
		    }
		}

		scanFile.close();
		Map<String, Integer> wordsMap = counter(words);
		printHashMap(wordsMap);
	}
	
	/**
	 * This method returns a Map where the keys are the words and the values are their frequency
	 * @param ArrayList<String> words
	 * @return Map<String, Integer>
	 */
	private static Map<String, Integer> counter (ArrayList<String> words){
		Map<String, Integer> map = new HashMap<String, Integer>();
	    int x;

	    for (int i = 0; i < words.size(); i++) {

	        if (map.containsKey(words.get(i).toLowerCase())) {
	            x = map.get(words.get(i).toLowerCase());
	            map.put(words.get(i).toLowerCase(), x + 1);
	        } else {
	            map.put(words.get(i).toLowerCase(), 1);
	        }
	    }

		return sortByComparator(map, false);
	}
	
	/**
	 * This method sorts in both ascending and descending ways a Map upon it's values.
	 * @param Map<String, Integer> unsortMap
	 * @param boolean order
	 * @return Map<String, Integer>
	 */
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
	
	/**
	 * This method prints all the keys and values of a given Map.
	 * @param Map<String, Integer> map
	 */
	private static void printHashMap(Map<String, Integer> map){
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

}
