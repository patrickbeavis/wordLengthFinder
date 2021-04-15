package wordLength;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WordLengthFinder {

	// Common strings
	private static String OUTPUT_STR = "Number of words of length ";
	private static String IS_STR = " is ";
	
	// Whitespace regex pattern - spaces, new lines, tabs
	private static final Pattern pattern = Pattern.compile("\\t|\\n| ");
	
	/*
	 * Main method:
	 * Reads file, then calls all other methods
	 */
	public static void main(String[] args) {
	    String path = args[0];
	    if (path == null) {
	    	System.out.println("A path to a .txt file should be provided");
	    	return;
	    }
	    
	    // Read in the file
	    File file = new File(path);
	    Scanner myReader = null;
	    
	    // Store all the words
	    List<String> wordsList = new ArrayList<>();
	    
		try {
			myReader = new Scanner(file);
			
		    while (myReader.hasNextLine()) {
		    	String line = myReader.nextLine();
		    	Arrays.asList(pattern.split(line)).forEach((word) -> wordsList.add(word));
		    }
		    
		    myReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
		}

		// Trim words of their trailing punctuation then
		// calculate the word lengths, then output
		handleOutput(wordsList.size(), findWordLengths(trimExtraCharacters(wordsList)));
	} 
	
	/*
	 * Remove extra punctuation
	 * 
	 * List<String> wordsSplit
	 * 	- the list of words split by whitespace
	 */
	private static List<String> trimExtraCharacters(final List<String> wordsSplit) {
		List<String> wordsWithoutExtras = new ArrayList<String>();
		
		for (String word : wordsSplit) {			
			// Check for possible trailing punctuation
			if (word.matches("(.*)[.|,|'|?|-|;|:|!|\"]")) {
				word = word.substring(0, word.length() - 1);
				
				// Special case for '...'
				if (word.endsWith("..")) {
					word = word.substring(0, word.length() -2);
				}
			}
			
			// Check for preceding punctuation
			if (word.matches("['|\"](.*)")) {
				word.substring(1);
			}
		
			wordsWithoutExtras.add(word);
		}
		
		return wordsWithoutExtras;
	}
	
	/*
	 * Create a map of word lengths to frequency of occurrence
	 * 
	 * List<String> wordsWithoutExtras
	 * 	- List of words with punctuation stripped from start/end
	 */
	private static Map<Integer, Integer> findWordLengths(final List<String> wordsWithoutExtras) {
		Map<Integer, Integer> lengthToFreq = new HashMap<>();

		// For every word, check the length, add into the map, or increase the value in the map
        for (int i = 0; i < wordsWithoutExtras.size(); ++i) {	
            
        	int length = wordsWithoutExtras.get(i).length();
        	Integer currentNumber = lengthToFreq.get(length);
        	
            if (currentNumber == null) {
                lengthToFreq.put(length, 1);
            }
            else {
                lengthToFreq.put(length, currentNumber+1);
            }
        }
        
		return lengthToFreq;
	}
	
	/*
	 * Calculate the required outputs and output them to console
	 * 
	 * int numWords
	 * 	- The number of words in the text file
	 * Map<Integer, Integer> wordLengthsMap
	 * 	- Map of: length of word -> frequency
	 */
	private static void handleOutput(final int numWords, final Map<Integer, Integer> wordLengthsMap) {
		// Output word count
		System.out.println("Word Count = " + numWords);
		
		// Find average word length and output
		double averageCalc = 0;
		for (Integer num : wordLengthsMap.keySet()) {
			averageCalc = num * wordLengthsMap.get(num);
		}
		
		System.out.println("The average word length is: " + String.format("%.4g%n", averageCalc/numWords));		
		
		// Output word lengths and frequencies
		wordLengthsMap.forEach(
				(key, val) -> System.out.println(OUTPUT_STR + key + IS_STR + val));
		
		// Find most frequent length
		int mostFreq = 0;
		for (Integer freq : wordLengthsMap.values()) {
			if (freq > mostFreq) {
				mostFreq = freq;
			}
		}
		
		//wordsLengthMap.forEach((key, val) -> {if(freq > maximum) { maximum = freq;}});
		
		List<Integer> wordsOfFreqLength = new ArrayList<>();
		for (Integer key : wordLengthsMap.keySet()) {
			if (wordLengthsMap.get(key) == mostFreq) {
				wordsOfFreqLength.add(key);
			}
		}
		
		// Output most frequently occurring length
		String frequentWords = wordsOfFreqLength.toString()
				.substring(1, wordsOfFreqLength.toString().length() -1);
		
		System.out.println(
				"The most frequently occurring word length is " + mostFreq 
				+ ". For words of length: " + frequentWords);
	}
}
