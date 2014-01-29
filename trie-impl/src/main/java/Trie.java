public class Trie
{
    private TrieNode root = new TrieNode();

    public void addWord(String word)
    {
        TrieNode node = root;
        char[] letters = word.toCharArray();

        for(char letter : letters)
        {
            node = node.forceChildWithLetter(letter);
        }
        node.markAsEndOfWord();
    }

    public boolean containsWord(String word)
    {
        TrieNode node = root;
        char[] letters = word.toCharArray();

        for(char letter: letters)
        {
            node = node.getChildWithLetter(letter);
            if(node == null)
            {
                return false;
            }
        }
        return node.isEndOfWord();
    }
}