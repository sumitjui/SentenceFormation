package com.marketlogic.suggestionformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * @author: Sumit Kumar Jui.
 * Class to form a logical statement out of the given array of words taking into consideration things like special characters and stop words.
 */
public class SuggestionFormer {

	public Set<String> formSentences(List<String> tokens) {
		// List of punctuation characters without spaces before.
		List<String> punctuationCharactersWithoutSpacesBefore = Arrays.asList(",", ".", ";", ":", ")", "}", "]");

		// Create a linked list for easy/fast traversal of characters without spaces before.
		List<String> punctuationCharactersWithoutSpacesBeforeList = new LinkedList<String>();
		punctuationCharactersWithoutSpacesBeforeList.addAll(punctuationCharactersWithoutSpacesBefore);

		// List of punctuation characters without spaces after.
		List<String> punctuationCharactersWithoutSpacesAfter = Arrays.asList("(", "[", "{", "\"", "");

		// Create a linked list for easy/fast traversal of characters without spaces after.
		List<String> punctuationCharactersWithoutSpacesAfterList = new LinkedList<String>();
		punctuationCharactersWithoutSpacesAfterList.addAll(punctuationCharactersWithoutSpacesAfter);

		// List of stop words.
		List<String> stopWords = Arrays.asList("is", "a", "can", "the", "");

		// Create a linked list for easy/fast traversal of stop words.
		List<String> stopWordsList = new LinkedList<String>();
		stopWordsList.addAll(stopWords);

		/*// Add an empty token at the beginning.
		tokens.add(0, "");*/

		// Use string builder to create the various permutations and combinations using the provided words.
		Set<String> suggestions = new HashSet<String>();
		Set<String> sentences = sentenceFormation(tokens, stopWordsList, punctuationCharactersWithoutSpacesBeforeList,
				punctuationCharactersWithoutSpacesAfterList, suggestions);
		sentences.remove("");
		return sentences;
	}

	/*
	 * Method to be called recursively in order to get all permutation and combinations of suggestions. 
	 */
	Set<String> sentenceFormation(List<String> tokens, List<String> stopWordsList,
			List<String> punctuationCharactersWithoutSpacesBeforeList,
			List<String> punctuationCharactersWithoutSpacesAfterList, Set<String> suggestions) {
		if (tokens.isEmpty()){
			return suggestions;
		}
		
		// Create a sentence on every iteration.
		StringBuilder suggestion = new StringBuilder();
		for (int innerCharactersIndex = 1; innerCharactersIndex < tokens.size(); innerCharactersIndex++) {
			if (!stopWordsList.contains(tokens.get(innerCharactersIndex).toLowerCase())
					&& tokens.get(innerCharactersIndex).toLowerCase().length() > 1) {
				if (punctuationCharactersWithoutSpacesBeforeList.contains(tokens.get(innerCharactersIndex))
						|| punctuationCharactersWithoutSpacesAfterList.contains(tokens.get(innerCharactersIndex - 1))) {
					suggestion.append(tokens.get(innerCharactersIndex));
				} else {
					suggestion.append(" " + tokens.get(innerCharactersIndex));
				}
			}

			if ("\"".equals(tokens.get(innerCharactersIndex - 1))) {
				if (punctuationCharactersWithoutSpacesAfterList.contains("\"")) {
					punctuationCharactersWithoutSpacesAfterList.remove("\"");
					punctuationCharactersWithoutSpacesBeforeList.add("\"");
				} else {
					punctuationCharactersWithoutSpacesAfterList.add("\"");
					punctuationCharactersWithoutSpacesBeforeList.remove("\"");
				}
			}

			suggestions.add(suggestion.toString());
		}
		tokens.remove(0);
		// Recursive call.
		sentenceFormation(tokens, stopWordsList, punctuationCharactersWithoutSpacesBeforeList, punctuationCharactersWithoutSpacesAfterList, suggestions);
		return suggestions;
	}

	/*
	 * Quick dirty testing prototype.
	 */
	public static void main(String[] args) {
		List<String> tokenStream = Arrays.asList("The", "beautiful", "girl", "from", "the", "farmers", "market", ".",
				"I", "like", "chewing", "gum", ".");
		List<String> tokenStreamList = new LinkedList<String>();
		tokenStreamList.addAll(tokenStream);
		SuggestionFormer suggestionFormer = new SuggestionFormer();
		Set<String> suggestions = suggestionFormer.formSentences(tokenStreamList);
		System.out.println(suggestions);
	}

}
