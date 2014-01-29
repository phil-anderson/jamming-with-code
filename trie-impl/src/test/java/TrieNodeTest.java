import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrieNodeTest
{
    @Test
    public void canHaveChildren()
    {
        TrieNode node = new TrieNode().forceChildWithLetter('p');
        assertThat(node.getChildWithLetter('h'), is(nullValue()));

        TrieNode aitchNode = node.forceChildWithLetter('h');
        assertThat(node.getChildWithLetter('h'), is(aitchNode));
    }

    @Test
    public void canMarkAsEndOfWord()
    {
        TrieNode node = new TrieNode().forceChildWithLetter('p');
        assertThat(node.isEndOfWord(), is(false));
        node.markAsEndOfWord();
        assertThat(node.isEndOfWord(), is(true));
    }

    @Test
    public void isCaseInsensitive()
    {
        TrieNode node = new TrieNode().forceChildWithLetter('p');
        TrieNode essNode = node.forceChildWithLetter('S');
        assertThat(node.getChildWithLetter('s'), is(essNode));
        TrieNode teeNode = node.forceChildWithLetter('t');
        assertThat(node.getChildWithLetter('T'), is(teeNode));
    }
}
