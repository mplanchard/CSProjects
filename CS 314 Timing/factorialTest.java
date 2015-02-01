/*
 * Class to test the accuracy of the factorial command from the timing.java class
 * Author: Matthew Planchard
 * Date: January 31 2014
 * UT EID: msp2377
 * Class: CS 314 - Novak
 */

public class factorialTest {
	public static void main(String [] args) {
		factorialRange(9000, 9000, 1);
		// factorialBlowup(10);
	}
	
	public static void compareFactorials(int n) {
		int integerFactorial = timing.fact((int) n);
		long longFactorial = timing.fact((long) n);
		Integer integerObjectN = (int) n;
		Integer integerObjectFactorial = timing.fact(integerObjectN);
		float floatFactorial = timing.fact((float) n);
		double doubleFactorial = timing.fact((double) n);
		System.out.println("Factorials of int " + n);
		System.out.println("int: " + integerFactorial);
		System.out.println("long: " + longFactorial);
		System.out.println("Integer: " + integerObjectFactorial);
		System.out.println("float: " + floatFactorial);
		System.out.println("double: " + doubleFactorial);
	}

	public static void compareFactorials(double n) {
		int integerFactorial = timing.fact((int) n);
		long longFactorial = timing.fact((long) n);
		Integer integerObjectN = (int) n;
		Integer integerObjectFactorial = timing.fact(integerObjectN);
		float floatFactorial = timing.fact((float) n);
		double doubleFactorial = timing.fact((double) n);
		System.out.println("Factorials of double " + n);
		System.out.println("int: " + integerFactorial);
		System.out.println("long: " + longFactorial);
		System.out.println("Integer: " + integerObjectFactorial);
		System.out.println("float: " + floatFactorial);
		System.out.println("double: " + doubleFactorial);
	}

	public static void compareFactorials(float n) {
		int integerFactorial = timing.fact((int) n);
		long longFactorial = timing.fact((long) n);
		Integer integerObjectN = (int) n;
		Integer integerObjectFactorial = timing.fact(integerObjectN);
		float floatFactorial = timing.fact((float) n);
		double doubleFactorial = timing.fact((double) n);
		System.out.println("Factorials of float " + n);
		System.out.println("int: " + integerFactorial);
		System.out.println("long: " + longFactorial);
		System.out.println("Integer: " + integerObjectFactorial);
		System.out.println("float: " + floatFactorial);
		System.out.println("double: " + doubleFactorial);
	} 

	public static void compareFactorials(long n) {
		int integerFactorial = timing.fact((int) n);
		long longFactorial = timing.fact((long) n);
		Integer integerObjectN = (int) n;
		Integer integerObjectFactorial = timing.fact(integerObjectN);
		float floatFactorial = timing.fact((float) n);
		double doubleFactorial = timing.fact((double) n);
		System.out.println("Factorials of long " + n);
		System.out.println("int: " + integerFactorial);
		System.out.println("long: " + longFactorial);
		System.out.println("Integer: " + integerObjectFactorial);
		System.out.println("float: " + floatFactorial);
		System.out.println("double: " + doubleFactorial);
	}

	public static void compareFactorials(Integer n) {
		int integerFactorial = timing.fact((int) n);
		long longFactorial = timing.fact((long) n);
		Integer integerObjectN = (int) n;
		Integer integerObjectFactorial = timing.fact(integerObjectN);
		float floatFactorial = timing.fact((float) n);
		double doubleFactorial = timing.fact((double) n);
		System.out.println("Factorials of Integer " + n);
		System.out.println("int: " + integerFactorial);
		System.out.println("long: " + longFactorial);
		System.out.println("Integer: " + integerObjectFactorial);
		System.out.println("float: " + floatFactorial);
		System.out.println("double: " + doubleFactorial);
	}

	public static void factorialBlowup(int n) {
		int multiplier = 1;
		while (multiplier < 1e5) {
			System.out.println("Factorial of " + (n * multiplier));
			compareFactorials(n * multiplier);
			multiplier *= 10;
		}
	}

	public static void factorialRange(int start, int end, int delta) {
		for (int i = start; i <= end; i += delta) {
			compareFactorials(i);
		}
	}  
	
	public static void factorialRange(int start, int end, double delta) {
	    for (double i = (double) start; i <= (double) end; i += delta) {
        	compareFactorials(i);
        }
    }
}
     
