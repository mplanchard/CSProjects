/* Timer Tests
 * - A class to test the timing of some methods from the timer class when n is altered
 * Author: Matthew Planchard
 * Date: 30 Jan 2014
 * EID: msp2377
 * Class: CS 314
 * Instructor: Novak
 */

import java.math.BigInteger;

public class timerTests {

	public static void main (String [] args) {
		timeDaffyDonald(30, 44);
		timeMGPG(1000, 256000);
//		timeMickey(1000, 8192000);
//		bigInt(1000, 64000);
	}

	public static void timeDaffyDonald(int startingValue, int endingValue) {
		Stopwatch timer = new Stopwatch();
		for (int i = startingValue; i <= endingValue; i++) {
			double totalTime = 0;
			for (int times = 0; times < 3; times++) {
				timer.start();
				timing.daffy(i);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;
			System.out.println("Daffy " + i + ": " + avgTime + " s");
		}

		for (int i = startingValue; i <= endingValue; i++) {
			double totalTime = 0;
			for (int times = 0; times < 3; times++) {
				timer.start();
				timing.donald(i);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;
			System.out.println("donald " + i + ": " + avgTime + " s");
		}
	}

	public static void timeMGPG(int startingArraySize, int endingArraySize) {
		Stopwatch timer = new Stopwatch();
		for (int i = startingArraySize; i <= endingArraySize; i *= 2) {
			double totalTime = 0;
			for (int times = 0; times < 3; times++) {
				int initArray[] = timing.randomarr(i);
				timer.start();
				timing.minnie(initArray);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;	
			System.out.println("Minnie" + i + ": " + avgTime + " s");		
		}

		for (int i = startingArraySize; i <= endingArraySize; i *= 2) {
			double totalTime = 0;
			for (int times = 0; times < 3; times++) {
				int initArray[] = timing.randomarr(i);
				timer.start();
				timing.goofy(initArray);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;
			System.out.println("Goofy" + i + ": " + avgTime + " s");		
		}

		for (int i = startingArraySize; i <= endingArraySize; i *= 2) {
			double totalTime = 0;
			for (int times = 0; times < 3; times++) {			
				int initArray[] = timing.randomarr(i);
				timer.start();
				timing.pluto(initArray);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;
			System.out.println("Pluto" + i + ": " + avgTime + " s");
		}

		for (int i = startingArraySize; i <= endingArraySize; i *= 2) {
			double totalTime = 0;
			for (int times = 0; times < 3; times++) {			
				int initArray[] = timing.randomarr(i);
				timing.pluto(initArray);
				timer.start();
				timing.gyro(initArray);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;
			System.out.println("Gyro" + i + ": " + avgTime + " s");
		}
	}

	public static void timeMickey(int startingArraySize, int endingArraySize) {
		Stopwatch timer = new Stopwatch();                                      	
		for (int i = startingArraySize; i <= endingArraySize; i *= 2) {
			double totalTime = 0;                               	
  			for (int times = 0; times < 3; times++) {							
				int initArray[] = timing.randomarr(i);
				timer.start();
				timing.mickey(initArray);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;
    		System.out.println("Mickey" + i + ": " + avgTime + " s");		
		}
	}
	
	public static void bigInt(int start, int end) {
		Stopwatch timer = new Stopwatch();
		for (int i = start; i <= end; i *= 2) {
			double totalTime = 0;                               	
			BigInteger bign = BigInteger.valueOf((long) i);
  			for (int times = 0; times < 3; times++) {	
				timer.start();
				timing.fact(bign);
				timer.stop();
				totalTime += timer.time();
			}
			double avgTime = totalTime / 3;
    		System.out.println("BigInt Factorial of " + i + ": " + avgTime + " s");		
		}
	}
}
