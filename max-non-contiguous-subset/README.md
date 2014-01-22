Max non-contiguous subset
=========================

This is my solution to a coding test question.

Given an array of numbers, find the maximum sum of numbers from the array such that no two numbers used in the sum
are next to each other.

For Example...
>   If the array was {4, 5, 8, 2} the answer would be 12 - the 4 plus the 8. We can't use the 5 instead of the 4
>   because it's next to the 8 which would then be ruled out.

This has some interesting corner cases, and writing a good set of unit tests isn't trivial. My fist approach used
straight recursion. This worked, but performance fell off a cliff pretty quickly as the size of the list increased.

So I then created a more performant sub-class which caches interim results - I've heard this sort of approach
described as dynamic programming, but it seems too simple for such a grandiose name. Either way, this version's
way, way faster.

It's still fairly heavy on the recursion though, and as a result can hit stack overflow on large lists. I have a
non-recursive approach in mind that would have negligible heap and stack impact, and also be alittle more
performant still, but I've already spent way more time than is healthy on this.

Interesting problem though.
