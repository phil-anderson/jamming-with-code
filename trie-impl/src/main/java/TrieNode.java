import java.util.HashMap;
import java.util.Map;

public class TrieNode
{
    private boolean endOfWord;

    private Map<Character, TrieNode> children = new HashMap<>();

    public TrieNode getChildWithLetter(char letter)
    {
        letter = Character.toUpperCase(letter);
        return children.get(letter);
    }

    public TrieNode forceChildWithLetter(char letter)
    {
        letter = Character.toUpperCase(letter);
        TrieNode child = getChildWithLetter(letter);
        if(child == null)
        {
            child = new TrieNode();
            children.put(letter, child);
        }
        return child;
    }

    public void markAsEndOfWord()
    {
        endOfWord = true;
    }

    public boolean isEndOfWord()
    {
        return endOfWord;
    }
}
