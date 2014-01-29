import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrieTest
{
    private Trie testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new Trie();
        testCandidate.addWord("cat");
        testCandidate.addWord("category");
        testCandidate.addWord("catapult");
        testCandidate.addWord("dog");
    }

    @Test
    public void addedWordsExistInTrie()
    {
        assertThat(testCandidate.containsWord("category"), is(true));
        assertThat(testCandidate.containsWord("dog"), is(true));
    }

    @Test
    public void notAddedWordsDontExistInTrie()
    {
        assertThat(testCandidate.containsWord("banana"), is(false));
        assertThat(testCandidate.containsWord("catamaran"), is(false));
    }

    @Test
    public void partialWordsOnlyMatchTheyWereAdded()
    {
        assertThat(testCandidate.containsWord("cat"), is(true));
        assertThat(testCandidate.containsWord("do"), is(false));
    }
}
