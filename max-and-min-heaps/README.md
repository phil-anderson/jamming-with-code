Max and min heap implementations
================================

I wanted to write a heap implementation to explore the algorithm. It seemed simple enough, and was, right up until
I got to implementing remove, which gave me considerable pause for thought.

This implementation is for ints, but could easily be genericised to handle anything.

A heap is a type of tree where the only rule is that child nodes are greater (or lower in the case of a min-heap) than
their parent. This means that the root of the tree is always the greatest (or lowest) item in the tree.

        10
       /  \
      3    9
     /    / \
    2    1   8

Although the nodes are not fully sorted (as they would be in a binary search tree), the numbers can be retrieved
in order by repeatedly removing the root node. This is known as a heap-sort and I use it in some of the unit tests to
assert the integrity of the heap.