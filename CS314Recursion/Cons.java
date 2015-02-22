/**
 * this class Cons implements a Lisp-like Cons cell
 * 
 * @author  Gordon S. Novak Jr.
 * @version 29 Nov 01; 25 Aug 08; 05 Sep 08; 08 Sep 08; 02 Sep 09; 27 Jan 10
 *          05 Feb 10; 16 Jul 10; 02 Sep 10; 13 Jul 11
 */

public class Cons
{
    // instance variables
    private Object car;
    private Cons cdr;
    private Cons(Object first, Cons rest)
       { car = first;
         cdr = rest; }

    // make a new Cons and put the arguments into it
    // add one new thing to the front of an existing list
    // cons("a", list("b", "c"))  =  (a b c)
    public static Cons cons(Object first, Cons rest)
      { return new Cons(first, rest); }

    // test whether argument is a Cons
    public static boolean consp (Object x)
       { return ( (x != null) && (x instanceof Cons) ); }

    // first thing in a list:    first(list("a", "b", "c")) = "a"
    // safe, returns null if lst is null
    public static Object first(Cons lst) {
        return ( (lst == null) ? null : lst.car  ); }

    // rest of a list after the first thing:
    //    rest(list("a", "b", "c")) = (b c)
    // safe, returns null if lst is null
    public static Cons rest(Cons lst) {
      return ( (lst == null) ? null : lst.cdr  ); }

    // second thing in a list:    second(list("a", "b", "c")) = "b"
    public static Object second (Cons x) { return first(rest(x)); }

    // third thing in a list:    third(list("a", "b", "c")) = "c"
    public static Object third (Cons x) { return first(rest(rest(x))); }

    // destructively replace the first
    public static void setfirst (Cons x, Object i) { x.car = i; }

    // destructively replace the rest
    public static void setrest  (Cons x, Cons y) { x.cdr = y; }

    // make a list of things:   list("a", "b", "c") = (a b c)
    public static Cons list(Object ... elements) {
       Cons list = null;
       for (int i = elements.length-1; i >= 0; i--) {
           list = cons(elements[i], list);
       }
       return list;
   }

    // convert a list to a string for printing
    public String toString() {
       return ( "(" + toStringb(this) ); }
    public static String toString(Cons lst) {
       return ( "(" + toStringb(lst) ); }
    private static String toStringb(Cons lst) {
       return ( (lst == null) ?  ")"
                : ( first(lst) == null ? "()" : first(lst).toString() )
                  + ((rest(lst) == null) ? ")" 
                     : " " + toStringb(rest(lst)) ) ); }

    public static int square(int x) { return x*x; }

    // ****** your code starts here ******

    // Sum of squares of integers from 1..n
    public static int sumsq (int n) {
    	if (n == 1) return 1;
    	else return (n*n + sumsq(n -1));
    }

    // Addition using Peano arithmetic
    public static int peanoplus(int x, int y) {
    	if (y == 0) return x;
    	else return peanoplus(++x, --y);
    }

    // Multiplication using Peano arithmetic
    public static int peanotimes(int x, int y) {
		if (y == 0) return 0;
		else if (y == 1) return x;
		else return peanoplus(x, peanotimes(x, --y));
    }

    // n choose k: distinct subsets of k items chosen from n items
    public static int choose(int n, int k) { 
		return (int) chooseb(n, k, 1);
    }

	// Helper function for choose
	public static double chooseb(int n, int k, int answer) {
		if (k > n) {
			System.out.println("You can't choose more items than you have!");
			return -1;
		}
		else if (n == 0 || n == 1 || k == 0 || n == k) return answer;
		else if (k == 1) return n;
		else return (double) n / k * chooseb(--n, --k, 1);
	}

    // Add up a list of Integer
    // iterative version, using while
	public static int sumlist (Cons lst) {
		int sum = 0;
		while ( lst != null ) {
			sum += (Integer) first(lst);   // cast since first() can be Object
			lst = rest(lst); }
		return sum; }

		// second iterative version, using for
	public static int sumlistb (Cons arg) {
	  int sum = 0;
	  for (Cons lst = arg ; lst != null; lst = rest(lst))
		sum += (Integer) first(lst);   // cast since first() can be Object
	  return sum; }

		// recursive version
	public static int sumlistr (Cons lst) {
		if (lst == null) return 0;
		else return (Integer) first(lst) + sumlistr(rest(lst));
	}

		// tail recursive version
	public static int sumlisttr (Cons lst) {
		return sumlisttrb(lst, 0);
	}

	// Helper function for sumlisttr
	public static Integer sumlisttrb(Cons lst, int answer) {
		if (lst == null) return answer;
		else return sumlisttrb(rest(lst), (Integer) first(lst) + answer);
	}

		// Sum of squared differences of elements of two lists
		// iterative version
	public static int sumsqdiff (Cons lst, Cons lstb) {
		if (lst == null || lstb == null) return 0;
		int result = 0;
		Cons lst1 = lstb;
		for (Cons lst0 = lst; lst0 != null; lst0 = rest(lst0)) {
			int diff = (Integer) first(lst0) - (Integer) first(lst1);
			result += diff * diff;
			lst1 = rest(lst1);
		}
		return result;
	}

		// recursive version
	public static int sumsqdiffr (Cons lst, Cons lstb) {
		if (lst == null || lstb == null) return 0;
		int diff = (Integer) first(lst) - (Integer) first(lstb);
		return (diff * diff) + sumsqdiffr(rest(lst), rest(lstb));
	}

		// tail recursive version
	public static int sumsqdifftr (Cons lst, Cons lstb) {
		return sumsqdifftrb(lst, lstb, 0);
	}

		// Helper function for sumsqdifftr
	public static Integer sumsqdifftrb(Cons lst, Cons lstb, int answer) {
		if (lst == null || lstb == null) return answer;
		int diff = (Integer) first(lst) - (Integer) first(lstb);
		return sumsqdifftrb(rest(lst), rest(lstb), diff * diff + answer);
	}

		// Find the maximum value in a list of Integer
		// iterative version
	public static int maxlist (Cons lst) {
		if (lst == null) return 0;
		int max = (Integer) first(lst);
		for (Cons lst0 = rest(lst); lst0 != null; lst0 = rest(lst0)) {
			max = Math.max((Integer) first(lst0), max);
		}	
		return max;
	}

		// recursive version
	public static int maxlistr (Cons lst) {
		if (lst == null) return Integer.MIN_VALUE;
		else return Math.max((Integer) first(lst), maxlistr(rest(lst)));
	}

		// tail recursive version
	public static int maxlisttr (Cons lst) {
		return maxlisttrb(lst, Integer.MIN_VALUE);
	}

	// Helper method for maxlisttr
	public static Integer maxlisttrb(Cons lst, Integer answer) {
		if (lst == null) return answer;
		else return maxlisttrb(rest(lst), Math.max((Integer) first(lst), answer));
	}

    // Make a list of Binomial coefficients
    // binomial(2) = (1 2 1)
    public static Cons binomial(int n) {
    	if (n == 0) return list(1);
		else {
			Cons preList = binomial(n - 1);
			Cons answer = list(1);
			for (Cons lst = preList; rest(lst) != null; lst = rest(lst)) {
				int num = (Integer) first(lst) + (Integer) second(lst);
				answer = cons(num, answer);
			}
			answer = cons(1, answer);
			return answer;
		}
    }


    // ****** your code ends here ******


    public static void main( String[] args )
      { 
        System.out.println("sumsq(5) = " + sumsq(5));

        System.out.println("peanoplus(3, 5) = " + peanoplus(3, 5));
        System.out.println("peanotimes(3, 5) = " + peanotimes(3, 5));
        System.out.println("peanotimes(30, 30) = " + peanotimes(30, 30));

        System.out.println("choose 5 3 = " + choose(5, 3));
        System.out.println("choose 100 3 = " + choose(100, 3));
        System.out.println("choose 20 10 = " + choose(20, 10));
        System.out.println("choose 100 5 = " + choose(100, 5));
        for (int i = 0; i <= 4; i++)
          System.out.println("choose 4 " + i + " = " + choose(4, i));

        Cons mylist = list(Integer.valueOf(3), Integer.valueOf(4),
                           Integer.valueOf(8), Integer.valueOf(2));
        Cons mylistb = list(Integer.valueOf(2), Integer.valueOf(1),
                           Integer.valueOf(6), Integer.valueOf(5));

        System.out.println("mylist = " + mylist);

        System.out.println("sumlist = " + sumlist(mylist));
        System.out.println("sumlistb = " + sumlistb(mylist));
        System.out.println("sumlistr = " + sumlistr(mylist));
        System.out.println("sumlisttr = " + sumlisttr(mylist));

        System.out.println("mylistb = " + mylistb);

        System.out.println("sumsqdiff = " + sumsqdiff(mylist, mylistb));
        System.out.println("sumsqdiffr = " + sumsqdiffr(mylist, mylistb));

        System.out.println("sumsqdifftr = " + sumsqdifftr(mylist, mylistb));

        System.out.println("maxlist " + mylist + " = " + maxlist(mylist));
        System.out.println("maxlistr = " + maxlistr(mylist));
        System.out.println("maxlisttr = " + maxlisttr(mylist));

        System.out.println("binomial(4) = " + binomial(4));
        System.out.println("binomial(20) = " + binomial(20));
      }

}
