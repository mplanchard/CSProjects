/**
 * this class Cons implements a Lisp-like Cons cell
 * 
 * @author  Gordon S. Novak Jr.
 * @version 29 Nov 01; 25 Aug 08; 05 Sep 08; 08 Sep 08; 12 Sep 08; 16 Feb 09
 *          01 Feb 12; 08 Feb 12; 22 Sep 13; 26 Dec 13
 */

/* Student code by Matthew Planchard, UT EID msp2377
 * Date: 15 February 2015
 * Class: CS314, Novak
 */

import java.lang.Math;

interface Functor { Object fn(Object x); }

interface Predicate { boolean pred(Object x); }

public class Cons
{
    // instance variables
    private Object car;   // traditional name for first
    private Cons cdr;     // "could-er", traditional name for rest
    private Cons(Object first, Cons rest)
       { car = first;
         cdr = rest; }

    // Cons is the data type.
    // cons() is the method that makes a new Cons and puts two pointers in it.
    // cons("a", null) = (a)
    // cons puts a new thing on the front of an existing list.
    // cons("a", list("b","c")) = (a b c)
    public static Cons cons(Object first, Cons rest)
      { return new Cons(first, rest); }

    // consp is true if x is a Cons, false if null or non-Cons Object
    public static boolean consp (Object x)
       { return ( (x != null) && (x instanceof Cons) ); }

    // first returns the first thing in a list.
    // first(list("a", "b", "c")) = "a"
    // safe, first(null) = null
    public static Object first(Cons lst) {
        return ( (lst == null) ? null : lst.car  ); }

    // rest of a list after the first thing.
    // rest(list("a", "b", "c")) = (b c)
    // safe, rest(null) = null
    public static Cons rest(Cons lst) {
      return ( (lst == null) ? null : lst.cdr  ); }

    // second thing in a list
    // second(list("+", "b", "c")) = "b"
    public static Object second (Cons x) { return first(rest(x)); }

    // third thing in a list
    // third(list("+", "b", "c")) = "c"
    public static Object third (Cons x) { return first(rest(rest(x))); }

    // destructively change the first() of a cons to be the specified object
    // setfirst(list("a", "b", "c"), 3) = (3 b c)
    public static void setfirst (Cons x, Object i) { x.car = i; }

    // destructively change the rest() of a cons to be the specified Cons
    // setrest(list("a", "b", "c"), null) = (a)     
    // setrest(list("a", "b", "c"), list("d","e")) = (a d e)
    public static void setrest  (Cons x, Cons y) { x.cdr = y; }

    // make a list of the specified items
    // list("a", "b", "c") = (a b c)
    // list() = null
   public static Cons list(Object ... elements) {
       Cons list = null;
       for (int i = elements.length-1; i >= 0; i--) {
           list = cons(elements[i], list);
       }
       return list;
   }

    // convert a list to a string in parenthesized form for printing
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


    // add up elements of a list of numbers
public static int sum (Cons lst) {
	return (lst == null) ? 0 : (Integer) first(lst) + sum(rest(lst));
}

    // mean = (sum x[i]) / n  
public static double mean (Cons lst) {
	return meanb(lst, 0, 0);
}

	// Helper method for the mean.
public static double meanb (Cons lst, double answer, int n) {
	return (lst == null) ? answer / n : meanb(rest(lst), (Integer) first(lst) + answer, ++n);
}

    // square of the mean = mean(lst)^2  
public static double sqmean (Cons lst) {
	double mean = mean(lst);
	return mean*mean;
}

    // mean square = (sum x[i]^2) / n  
public static double meansq (Cons lst) {
	return meansqb (lst, 0, 0);
}

	// Helper method for obtaining mean square.
public static double meansqb (Cons lst, double answer, int n) {
	return (lst == null) ? answer / n : meansqb(rest(lst), (Integer) first(lst) * (Integer) first(lst) + answer, ++n);
}

	// Return the variance of the numbers in a list.
	// Variance is the mean square minus the square of the mean.
	// Luckily we've already made functions to do both of these things.
public static double variance (Cons lst) {
	return (lst == null) ? 0 : meansq(lst) - sqmean(lst);
}

	// Return the standard deviation of the numbers in a list.
public static double stddev (Cons lst) {
	return (lst == null) ? 0 : Math.sqrt(variance(lst));
}

	// Return the sine of a number.
public static double sine (double x) {
	return sineb(x, 0, 1);
}

	// Helper method for sine
public static double sineb (double x, double answer, int seriesNum) {
	if (x == 0 || seriesNum == 12) return answer;
	else return sineb(x, answer + calcSeries(x, seriesNum), ++seriesNum);
}

	// Method to calculate an individual unit of the Taylor series.
public static double calcSeries (double x, int seriesNum) {
	int multFactor = 1;
	if (seriesNum % 2 == 0)
		multFactor = -1;
	int expFactor = seriesNum * 2 - 1;
	return multFactor * exp(x, expFactor) / fact(expFactor);
}

	// Quick method to calculate exponents for the Taylor series
public static double exp (double x, int exp) {
	return expb(x, exp, 1);
}

	// Helper method for exponent
public static double expb (double x, int exp, double answer) {
	if (exp == 0) return answer;
	else if (x == 0) return 0;
	else return expb(x, --exp, x * answer);
}

	// Method to calculate factorial for the Taylor series.
public static double fact (double x) {
	return (x == 0) ? 1 : x * fact(x-1);
}

	// Method to calculate length of a list. Useful for the below methods.
public static int length (Cons lst) {
	return lengthb(lst, 0);
}

	// Helper method for length
public static int lengthb (Cons lst, int answer) {
	return (lst == null) ? answer : lengthb(rest(lst), ++answer);
}

	// Method to return the result of calling rest n times on a list.
public static Cons nthcdr (int n, Cons lst) {
	if (lst == null || n > length(lst)) return null;
	else return (n == 0) ? lst : nthcdr(--n, rest(lst));
}

	// Method to return the nth item of a list.
public static Object elt (Cons lst, int n) {
	if (lst == null) return null;
	else if (n > length(lst) - 1) return "List not long enough!";
	else return (n == 0) ? first(lst) : elt(rest(lst), --n);
}

	// Method to interpolate the value of x, given a list where x = 0 is the first item in
	// the list and so on. Interpolation is linear.
public static double interpolate (Cons lst, double x) {
	if (lst == null) return 0;
	else if (x % 1 == 0) return (double) elt(lst, (int) x);
	else {
		double lsti = (Integer) elt(lst, (int) x)*1.0;
		double lstiPlus = (Integer) elt(lst, (int) x + 1)*1.0;
		return lsti + (x - (int) x) * (lstiPlus - lsti);
	}
}

	// Method to recursively sum a list and any sublists
public static int sumtr (Cons lst) {
	return sumtrb(lst, 0);
}

	// Helper method for sumtr.
public static int sumtrb (Cons lst, int answer) {
	if (lst == null) return answer;
	else if (consp(first(lst))) return sumtrb(rest(lst), sumtr((Cons) first(lst)) + answer);
	else return sumtrb(rest(lst), (Integer) first(lst) + answer);
}

    // Returns a subsequence of a list from item start to item end. 
public static Cons subseq (Cons lst, int start, int end) {
	return subseqb(nthcdr(start, lst), start, end, list());
}

	// Helper mether for subseq
public static Cons subseqb (Cons lst, int start, int end, Cons answer) {
	if (start == end) return answer;
	else return nreverse(subseqb(rest(lst), ++start, end, cons(first(lst), answer)));
}

	// Non-destructively reverse a list
public static Cons reverse (Cons lst) {
	return reverseb(lst, list());
}

	// Helper method for reverse
public static Cons reverseb (Cons lst, Cons answer) {
	if (lst == null) return answer;
	else return reverseb(rest(lst), cons(first(lst), answer));
}

	// Destructively reverse a list
public static Cons nreverse (Cons lst) {
	Cons end = null;
	Cons tmp;
	while (lst != null) {
		tmp = rest(lst);
		setrest(lst, end);
		end = lst;
		lst = tmp;
	}
	return end;
}

	// Return list containing only the postiive values of the original list
public static Cons posfilter (Cons lst) {
	return posfilterb(lst, list());
}

	// Helper method for positive filter
public static Cons posfilterb (Cons lst, Cons answer) {
	if (lst == null) return answer;
	else return nreverse(((Integer) first(lst) >= 0) ? posfilterb(rest(lst), cons(first(lst), answer)) : posfilterb(rest(lst), answer));
}

	// Method to obtain only subsets that match the predicate condition
public static Cons subset (Predicate p, Cons lst) {	
	return subsetb (p, lst, list());
}

	// Helper method for subset
public static Cons subsetb (Predicate p, Cons lst, Cons answer) {
	if (lst == null) return answer;
	else return nreverse((p.pred(first(lst))) ? subsetb(p, rest(lst), cons(first(lst), answer)) : subsetb(p, rest(lst), answer)); 
}

	// Method to map a list onto a new list, applying some functor f
public static Cons mapcar (Functor f, Cons lst) {
	return mapcarb(f, lst, list());	
}

	// Helper function for mapcar
public static Cons mapcarb (Functor f, Cons lst, Cons answer) {
	if (lst == null) return answer;
	else return nreverse(mapcarb(f, rest(lst), cons(f.fn(first(lst)), answer)));
}

	// Returns the first value of set lst for which predicate p is true
public static Object some (Predicate p, Cons lst) {
	if (lst == null) return "There exists no x for which p is true in this set!";
	else if (p.pred(first(lst))) return first(lst);
	else return some(p, rest(lst));
}

	// True if and only if predicate p is true for every value in set lst
public static boolean every (Predicate p, Cons lst) {
	if (lst == null) return true; // All Martians have purple toes is a true statement, since there are no Martians.
	else return (p.pred(first(lst))) ? every(p, rest(lst)) : false; 
}

	// Returns a list of binomials for a given exponential value n.
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
        Cons mylist =
            list(95, 72, 86, 70, 97, 72, 52, 88, 77, 94, 91, 79,
                 61, 77, 99, 70, 91 );
        System.out.println("mylist = " + mylist.toString());
        System.out.println("sum = " + sum(mylist));
        System.out.println("mean = " + mean(mylist));
        System.out.println("meansq = " + meansq(mylist));
        System.out.println("variance = " + variance(mylist));
        System.out.println("stddev = " + stddev(mylist));
        System.out.println("sine(0.5) = " + sine(0.5));  // 0.47942553860420301
		// My tests
		System.out.println("Length of mylist: " + length(mylist));
		System.out.println("sine(3.1415926) = " + sine(3.1415926));
		System.out.println("sine(0) = " + sine(0));
		System.out.println("sine(pi/2) = " + sine(3.1415926/2));
		System.out.println("2^3 = " + exp(2,3));
		System.out.println("3! = " + fact(3));
		System.out.println("Reverse test");
		Cons testList = list(0, 1, 2, 3, 4, 5);
		System.out.println(testList);
		System.out.println(reverse(testList));
		System.out.println(nreverse(testList));
		// End of my tests
        System.out.print("nthcdr 5 = ");
        System.out.println(nthcdr(5, mylist));
        System.out.print("nthcdr 18 = ");
        System.out.println(nthcdr(18, mylist));
        System.out.println("elt 5 = " + elt(mylist,5));

        Cons mylistb = list(0, 30, 56, 78, 96);
        System.out.println("mylistb = " + mylistb.toString());
        System.out.println("interpolate(3.4) = " + interpolate(mylistb, 3.4));
        Cons binom = binomial(12);
        System.out.println("binom = " + binom.toString());
        System.out.println("interpolate(3.4) = " + interpolate(binom, 3.4));

        Cons mylistc = list(1, list(2, 3), list(list(list(list(4)),
                                                     list(5)),
                                                6));
        System.out.println("mylistc = " + mylistc.toString());
        System.out.println("sumtr = " + sumtr(mylistc));
        Cons mylistcc = list(1, list(7, list(list(2), 3)),
                             list(list(list(list(list(list(list(4)))), 9))),
                             list(list(list(list(5), 4), 3)),
                             list(6));
        System.out.println("mylistcc = " + mylistcc.toString());
        System.out.println("sumtr = " + sumtr(mylistcc));

        Cons mylistd = list(0, 1, 2, 3, 4, 5, 6 );
        System.out.println("mylistd = " + mylistd.toString());
        System.out.println("subseq(2 5) = " + subseq(mylistd, 2, 5));

        Cons myliste = list(3, 17, -2, 0, -3, 4, -5, 12 );
        System.out.println("myliste = " + myliste.toString());
        System.out.println("posfilter = " + posfilter(myliste));

        final Predicate myp = new Predicate()
            { public boolean pred (Object x)
                { return ( (Integer) x > 3); }};

        System.out.println("subset = " + subset(myp, myliste).toString());

        final Functor myf = new Functor()
            { public Integer fn (Object x)
                { return  (Integer) x + 2; }};

        System.out.println("mapcar = " + mapcar(myf, mylistd).toString());

        System.out.println("some = " + some(myp, myliste).toString());
        // Another personal test:
        System.out.println("some(no result) = " + some(myp, list(0, 1, 2, 3)).toString());

        System.out.println("every = " + every(myp, myliste));
        // Another personal test:
        System.out.println("every(true) = " + every(myp, list(4, 5, 4, 6, 7)));

      }

}
