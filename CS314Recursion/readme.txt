Matthew S. Planchard
EID: msp2377
08 February 2015
CS 314 - Novak

You'll want to turn on word wrap, or this will be a pain to read.

2.	An invariant property of peanoplus is that it always produces postive output for any proper input. Another invariant property is that the function will be called a number of times exactly equal to y, unless y is zero. Its Big O is n, where n is directly proportional to y.

3.	The Big O of peanotimes is also n, with n directly proportional to y, because peanotimes is really just kind of a wrapper for peanoplus, calling it y-1 times and then adding that to x. Functionally, it's no different from calling peanoplus an equivalent number of times when performing addition.

8.	My output for the binomial factors for 0-4 is below, followed by my output for choose n of k for n = 4, k = {0-4}:
		(1)
		(1 1)
		(1 2 1)
		(1 3 3 1)
		(1 4 6 4 1)
		1
		4
		6
		4
		1
	It seems as though the binomial factors of a number are equivalent to the values of n choose k for all k >= 0 and >= n.
