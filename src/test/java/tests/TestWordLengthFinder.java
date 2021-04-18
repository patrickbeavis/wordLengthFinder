package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import wordLengthFinder.WordLengthFinder;

/*
 * Class for testing
 * Wanted to avoid polluting GitHub with test .txt files so none are used. 
 * Scanner class used to read files is 3rd party and well tested 
 * - should be fine without testing again here
 */
public class TestWordLengthFinder {

	// Quick check to ensure one-liner works as intended
	// Avoids breaking line out into method but not maintainable.
	@Test
	public void TestCopiedLineForSplittingStringToWords() {
		List<String> wordsList = new ArrayList<>();
		
		String line = "this is an example line";
		
		Arrays.asList(WordLengthFinder.pattern.split(line)).forEach((word) -> wordsList.add(word));
		
		assertEquals("[this, is, an, example, line]", wordsList.toString());
	}
	
	@Test
	public void TestGivenWordsWithTrailingPunc_WhenStripped_TrailingPuncRemoved() {
		List<String> withPunc = Arrays.asList("hello?", "world!", "one-two");
		List<String> results = Arrays.asList("hello", "world", "one-two");
		
		assertEquals(results, WordLengthFinder.trimExtraCharacters(withPunc));
		
		// Test special case for ellipses being removed too
		List<String> dotdotdot = Arrays.asList("hello...");
		List<String> removed = Arrays.asList("hello");
		assertEquals(removed, WordLengthFinder.trimExtraCharacters(dotdotdot));
	}
	
	@Test
	public void TestGivenWordsWithPreceedingPunc_WhenStripped_PreceedingPuncRemoved() {
		List<String> withPunc = Arrays.asList("'hello", "\"world", "one-two", "?hello");
		List<String> results = Arrays.asList("hello", "world", "one-two", "?hello");
		
		assertEquals(results, WordLengthFinder.trimExtraCharacters(withPunc));
	}
		
	@Test
	public void TestGivenAListOfWords_WhenLengthsFound_MapAccurate() {
		List<String> words = Arrays.asList("hello", "world!");
		
		Map<Integer, Integer> result = new HashMap<>();
		result.put(5, 1);
		result.put(6, 1);
		
		assertEquals(result, WordLengthFinder.findWordLengths(words));
	}
	
	@Test
	public void TestGivenMostFrequentWordLength_WhenCalculated_ThenCorrectWordLengthsOutput() {
		Map<Integer, Integer> words = new HashMap<>();
		
		words.put(3, 1);
		words.put(6, 2);
		words.put(8, 7);
		words.put(2, 7);
		
		assertEquals("[2, 8]", WordLengthFinder.findMostFrequentWordLengths(7, words).toString());
	}
	
	@Test
	public void TestGivenAWordLength_WhenSearchedFor_ThenAssociatedWordLengthFound() {
		Map<Integer, Integer> words = new HashMap<>();
		
		words.put(3, 4);
		words.put(6, 9);
		
		assertEquals("[3]", WordLengthFinder.findMostFrequentWordLengths(4, words).toString());
	}
	
	@Test
	public void TestGivenProcessedWords_WhenCalculated_AverageLengthCorrect() {
		
		Map<Integer, Integer> words = new HashMap<>();
		
		words.put(1, 5);
		words.put(2, 0);
		words.put(3, 5); // Average = 2
		
		int numWords = 10;
		
		assertTrue(2 == WordLengthFinder.calculateAverageLengthOfWords(numWords, words));
	}
	
	@Test
	public void TestGivenOtherProcessedWords_WhenCalculated_AverageLengthCorrect() {
		Map<Integer, Integer> words = new HashMap<>();
		
		words.put(5, 3);
		words.put(9, 1);
		words.put(10, 4); // Average = 8
		
		int numWords = 8;
		
		assertTrue(8 == WordLengthFinder.calculateAverageLengthOfWords(numWords, words));
	}

}
