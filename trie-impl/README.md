Trie Implementation
===================

A came across the Trie as a data structure for holding lists of words as part of a code kata I ran for the London team
and thought I'd have a go at implementing one. This one stores words, but they can be used to store any data that
consists of a sequence of common values (in this case letters).

The idea is that you build a tree of letters that form words, however initial letters are shared. This means that any
traversal through the tree that starts form root and ends at a leaf will spell out a word that was added.

e.g. The following trie stores the words 'dog' and 'dot'...

      D
      |
      O
     / \
    G   T

Additionally, letters that are at the end of a word have a flag set. This is to cover the case wherethe word being
added already exists in the trie as the start of another word.

e.g. If we added the word 'do' to the trie above we need to flag the O as the end of a word. If we mark all word
terminators in this way, the trie now looks as follows (an asterisk denotes the end of a word and I've added
the word 'dad' to flesh it out a bit)...

        D
       / \
      O*  A
     / \   \
    G*  T*  D*

Finally, there's a root node above the D that would have a child for any other initial letters of words that that
may be added.