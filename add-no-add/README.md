Adding numbers without mathematical operators
=============================================

This very short code snippet is my answer to a coding test question whereby you have to add two integers together
without using any arithmetic operations (i.e. only bitwise operators).

The approach is to use an XOR to effectively add without carry, and use AND with a shift left to work out what the
carry would be. Adding the two (by recursion) gives the answer (the recursion ends when there's no carry to add.