Counting the number of unique routes
====================================

This was my solution to a coding test question about a Robot sitting on one square of a grid.

It has to get to a destination square by moving one square at a time. Each move can be either one square to the
right, or one square down (the destination is never above or to the left of the start point). It's entirely at the
robot's discretion.

The question is... How many different unique routes there from the start to the finish given his movement
capabilities?

I've used a recursive approach here (with caching of interim results) which is neat and tidy but - as with most
recursive solutions to this sort of problem - will cane the stack hard. A non-recursive approach wouldn't be hard to
write and would probably be faster. I'll give it a go tomorrow!