The Big O of the two functions seem as though they should be similar. Size() from the 
LinkedList library seems like it would require a walk down the list, similar to how
getting the midpoint for the llmergesort method requires a walk down the list. From there,
the method is recursively called until we get down to one item. The order of either
function is n, because each walk will take half as much time as the next, but you still
have to do at least one walk.

The garbage collection Big O is potentially quite large. Each time the function is called
(so approximately n/2 times), two new lists are created (total of n lists). Only two are
retained (total of n - 2 garbage lists), so there are a lot of lists to be cleaned up.
