Find pairs of numbers that make a sum
=====================================

This is my solution to a coding test question.

You're given an array of integers, and a target number, and have to find pairs of numbers that sum up to the target
number. Once a number has been used for a pair, you can't use it again.

I coded up a little Bag implementation which is an efficient way of storing a collection with losts of duplicates
(effectively a map of values to counts) and used that to hold values any values that haven't been used for a pair yet,
and removing any that do get used.