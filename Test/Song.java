/*
 * CS312 Assignment 1.
 * On my honor, Matthew S. Planchard, this programming assignment is my own work.
 * Section Number: 52827
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 *
 *  Name: Matthew Planchard
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Section 5 digit ID: 52827
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

public class Song {
	public static void main(String[] args) {
		//Verse One
		System.out.println("There was an old woman who swallowed a fly.");
		VerseOneCoda();
		System.out.println();
		//Verse Two
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wriggled and iggled and jiggled inside her.");
		VerseTwoCoda();
		System.out.println();
		//Verse Three
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		VerseThreeCoda();
		System.out.println();
		//Verse Four
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		VerseFourCoda();
		System.out.println();
		//Verse Five
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
		VerseFiveCoda();
		System.out.println();
		//Verse Six
		System.out.println("There was an old woman who swallowed a goat,");
		System.out.println("She just opened her throat to swallow a goat.");
		VerseSixCoda();
		System.out.println();
		//Verse Seven
		System.out.println("There was an old woman who swallowed a cow,");
		System.out.println("I don't know how she swallowed a cow.");
		VerseSevenCoda();
		System.out.println();
		//Coda
		System.out.println("There was an old woman who swallowed a horse,");
		System.out.println("She died of course.");
	}
	public static void VerseOneCoda() {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
	}
	public static void VerseTwoCoda() {
		System.out.println("She swallowed the spider to catch the fly,");
		VerseOneCoda();
	}
	public static void VerseThreeCoda() {
		System.out.println("She swallowed the bird to catch the spider,");
		VerseTwoCoda();
	}
	public static void VerseFourCoda() {
		System.out.println("She swallowed the cat to catch the bird,");
		VerseThreeCoda();
	}
	public static void VerseFiveCoda() {
		System.out.println("She swallowed the dog to catch the cat,");
		VerseFourCoda();
	}
	public static void VerseSixCoda() {
		System.out.println("She swallowed the goat to catch the dog,");
		VerseFiveCoda();
	}
	public static void VerseSevenCoda() {
		System.out.println("She swallowed the cow to catch the goat,");
		VerseSixCoda();
	}
}
