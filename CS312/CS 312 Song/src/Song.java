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
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 */

public class Song {
	public static void main(String[] args) {
		
		//Verse One
		System.out.println("There was an old woman who swallowed a fly.");
		verseOneCoda();
		
		//Verse Two
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wriggled and iggled and jiggled inside her.");
		verseTwoCoda();
		
		//Verse Three
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		verseThreeCoda();
		
		//Verse Four
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		verseFourCoda();
		
		//Verse Five
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
		verseFiveCoda();
		
		//Verse Six
		System.out.println("There was an old woman who swallowed a goat,");
		System.out.println("She just opened her throat to swallow a goat.");
		verseSixCoda();
		
		//Verse Seven
		System.out.println("There was an old woman who swallowed a cow,");
		System.out.println("I don't know how she swallowed a cow.");
		verseSevenCoda();
		
		//Coda
		System.out.println("There was an old woman who swallowed a horse,");
		System.out.println("She died of course.");
	}
	
// Nested methods for each verse's coda, starting with the first and moving down through the seventh.
	
	// Verse one, fly
	public static void verseOneCoda() {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
		System.out.println();
	}
	
	// Verse two, spider
	public static void verseTwoCoda() {
		System.out.println("She swallowed the spider to catch the fly,");
		verseOneCoda();
	}
	
	// Verse three, bird
	public static void verseThreeCoda() {
		System.out.println("She swallowed the bird to catch the spider,");
		verseTwoCoda();
	}
	
	// Verse four, cat
	public static void verseFourCoda() {
		System.out.println("She swallowed the cat to catch the bird,");
		verseThreeCoda();
	}
	
	// Verse five, dog
	public static void verseFiveCoda() {
		System.out.println("She swallowed the dog to catch the cat,");
		verseFourCoda();
	}
	
	// Verse six, goat
	public static void verseSixCoda() {
		System.out.println("She swallowed the goat to catch the dog,");
		verseFiveCoda();
	}
	
	// Verse seven, cow
	public static void verseSevenCoda() {
		System.out.println("She swallowed the cow to catch the goat,");
		verseSixCoda();
	}
	
}
