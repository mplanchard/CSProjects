/* Author: Matthew Planchard
 * Date: February 08 2015
 * Class: CS 314 - Novak
 * EID: msp2377
 *
 * This is a program to demonstrate recurisve functionality and to work
 * with lists.
 *
 */

import java.lang.Math;

public class Recursion {
	
	/* Recursive function to calculate the sum of the squares from 1 to a
	* provided integer n. Will break with large numbers. */
	public static int sumsq(int n) {
		if (n == 1) return 1;
		else return (n*n + sumsq(n -1));
	}

	// Recursive function to perform addition of y onto x. Only valid for nonnegative integers.
	public static int peanoplus(int x, int y) {
		if (y == 0) return x;
		else return peanoplus(++x, --y);
	}

	// Function to multiply x by y, using only peanoplus and --. Only valid for nonnegative integers.
	public static int peanotimes(int x, int y) {
		if (y == 0) return 0;
		else if (y == 1) return x;
		else return peanoplus(x, peanotimes(x, --y));
	}

	// Function to return number of possible sets of k items from n total items.
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

	// Returns the sum of numbers in a list
	public static Integer sumlistr(Cons lst) {
		if (lst == null) return 0;
		else return (Integer)Cons.first(lst) + sumlistr(Cons.rest(lst));
	}

	// Returns the sumer of numbers in a list, tail recursive
	public static Integer sumlisttr(Cons lst) {
		return sumlisttrb(lst, 0);
	}

	// Helper function for sumlisttr
	public static Integer sumlisttrb(Cons lst, int answer) {
		if (lst == null) return answer;
		else return sumlisttrb(Cons.rest(lst), (Integer) Cons.first(lst) + answer);
	}

	// Returns the sum of squares for two lists. Lists must be the same length.
	public static Integer sumsqdiff(Cons lsta, Cons lstb) {
		if (Cons.length(lsta) != Cons.length(lstb)) {
			System.out.println("Error! Lists must be the same length.");
			return -1;
		}
		else if (lsta == null) return 0;
		int result = 0;
		Cons lst1 = lstb;
		for (Cons lst0 = lsta; lst0 != null; lst0 = Cons.rest(lst0)) {
			int diff = (Integer) Cons.first(lst0) - (Integer) Cons.first(lst1);
			result += diff * diff;
			lst1 = Cons.rest(lst1);
		}
		return result;
	}

	// Sum of squares for two lists, recursive.
	public static Integer sumsqdiffr(Cons lsta, Cons lstb) {
		if (Cons.length(lsta) != Cons.length(lstb)) {
			System.out.println("Error! Lists must be the same length.");
			return -1;
		}
		else if (lsta == null) return 0;
		int diff = (Integer) Cons.first(lsta) - (Integer) Cons.first(lstb);
		return (diff * diff) + sumsqdiffr(Cons.rest(lsta), Cons.rest(lstb));
	}

	// Sum of squares of two lists, tail recursive.
	public static Integer sumsqdifftr(Cons lsta, Cons lstb) {
		return sumsqdifftrb(lsta, lstb, 0);
	}

	// Helper function for sumsqdifftr
	public static Integer sumsqdifftrb(Cons lsta, Cons lstb, int answer) {
		if (Cons.length(lsta) != Cons.length(lstb)) {
    		System.out.println("Error! Lists must be the same length.");
    		return -1;
    	}
		else if (lsta == null) return answer;
		int diff = (Integer) Cons.first(lsta) - (Integer) Cons.first(lstb);
		return sumsqdifftrb(Cons.rest(lsta), Cons.rest(lstb), diff * diff + answer);
	}

	// Function to find the max value in a list
	public static Integer maxlist(Cons lst) {
		if (lst == null) return 0;
		int max = (Integer) Cons.first(lst);
		for (Cons lst0 = Cons.rest(lst); lst0 != null; lst0 = Cons.rest(lst0)) {
			max = Math.max((Integer) Cons.first(lst0), max);
		}	
		return max;
	}

	// Find the max value, recursively
	public static Integer maxlistr(Cons lst) {
		if (lst == null) return Integer.MIN_VALUE;
		else return Math.max((Integer) Cons.first(lst), maxlistr(Cons.rest(lst)));
	}

	// Find the max value, tail recursive
	public static Integer maxlisttr(Cons lst) {
		return maxlisttrb(lst, Integer.MIN_VALUE);
	}

	// Helper method for maxlisttr
	public static Integer maxlisttrb(Cons lst, Integer answer) {
		if (lst == null) return answer;
		else return maxlisttrb(Cons.rest(lst), Math.max((Integer) Cons.first(lst), answer));
	}
	
	// Calculate binomial coefficients of n.
	public static Cons binomial(int n) {
		if (n == 0) return Cons.list(1);
		else {
			Cons preList = binomial(n - 1);
			Cons answer = Cons.list(1);
			for (Cons lst = preList; Cons.rest(lst) != null; lst = Cons.rest(lst)) {
				int num = (Integer) Cons.first(lst) + (Integer) Cons.second(lst);
				answer = Cons.cons(num, answer);
			}
			answer = Cons.cons(1, answer);
			return answer;
		}
	}
}
