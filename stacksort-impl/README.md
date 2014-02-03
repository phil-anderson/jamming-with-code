Stacksort Implementation
========================

Here's a sort algortithm you don't see every day! I'm not entirely sure what it'd ever be used for, although if
adding is infrequent and not performance sensitive but retrieving the list in order needs to be really fast then maybe
this algorithm might come into its own.

It works by maintaining a stack in order. When a number is added, if it is greater than the current head it is
simply added, otherwise numbers are popped off the head of the stack and pushed onto a temporary one in turn until
the condition is met, at which time the number is pushed onto the main stack and the numbers in the temporary stack
are marshelled back in after it (i.e. by being popped off the temporary stack and pushed back onto the main one in
turn).

It's basically the same as putting railway carriages in order using a railway-siding!