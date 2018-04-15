package com.marketlogic.suggestionformer;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class SuggestionFormerTest {
	
	@Test
    public void testSuggestionFormer() {
		List<String> testStringList = Arrays.asList(" like"," beautiful girl from farmers market like chewing gum"," from farmers"," beautiful girl"," like chewing gum"," beautiful girl from farmers market like chewing"," girl"," market"," gum"," from farmers market like chewing gum"," girl from farmers market like chewing gum"," girl from farmers"," girl from farmers market like"," farmers market"," from farmers market like"," girl from"," beautiful girl from"," beautiful girl from farmers"," beautiful"," market like chewing"," chewing"," market like"," farmers market like chewing gum"," chewing gum"," from farmers market"," farmers"," beautiful girl from farmers market"," girl from farmers market like chewing"," girl from farmers market"," farmers market like chewing"," from"," farmers market like"," market like chewing gum"," beautiful girl from farmers market like"," from farmers market like chewing"," like chewing");
	    List<String> actual = new LinkedList<String>();
        List<String> tokenStream = Arrays.asList("The", "beautiful", "girl", "from", "the", "farmers", "market", ".", "I", "like", "chewing", "gum", ".");
		List<String> tokens = new LinkedList<String>();
		tokens.addAll(tokenStream);
        actual.addAll(new SuggestionFormer().formSentences(tokens));
        List<String> expected = new LinkedList<String>();
        expected.addAll(testStringList);
        assertEquals(expected, actual);
    }
}
