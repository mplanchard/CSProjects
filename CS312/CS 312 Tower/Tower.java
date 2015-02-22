/*
 *  CS312 Assignment 2.
 *  On my honor, Matthew Planchard, this programming assignment is my own work.
 *
 *  A program to print out the UT Tower in ASCII art form.
 *
 *  Name: Matthew Planchard
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Section 5 digit ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 */

public class Tower {
	
	// CS312 students, DO NOT ALTER THE FOLLOWING LINE.
	public static final int SIZE = TowerSize.getSize();

	// Main method to print the tower, calls other methods to print each section
    public static void main(String[] args) {
    	towerTop();
    	towerMiddle();
    	towerBottom();
    }
        
    // Print the top of the tower
    public static void towerTop() {
    	towerTopHashtags();
    	int towerTopRows = (SIZE * 2) - 2;
    	for (int row = 0; row < towerTopRows; row++) {
        	int towerTopSpaces = (SIZE * 4) + 2;
        	int towerTopColumns = SIZE * 2 - 1;
        	for (int j = 0; j < towerTopSpaces; j++) {
        		System.out.print(" ");
        	}
    		for (int j = 0; j < towerTopColumns; j++) {
    			System.out.print("|");
    		}
    		System.out.println();
    	}
    	towerTopHashtags();
    }
    
    // Print the middle of the tower
    public static void towerMiddle() {
    	int rowsOfWindows = SIZE * SIZE;
    	for (int i = 1; i <= rowsOfWindows; i++) {
    		towerMidTildes();
        	towerMidWindows();
    	}
    	towerMidTildes();
    }
    
    // Print the bottom of the tower
    public static void towerBottom() {
    	towerBottomTopOfBase();
    	towerBottomWindows();
    }
    
    // Print hashtags at the top of the tower
    public static void towerTopHashtags() {
    	int towerTopSpaces = SIZE * 4 + 2;
    	int towerTopColumns = SIZE * 2 - 1;
    	for (int i = 1; i <= towerTopSpaces; i++) {
    		System.out.print(" ");
    	}
    	for (int i = 1; i <= towerTopColumns; i++) {
    		System.out.print("#");
    	}
    	System.out.println();
    }
    
    /* Print tildes for middle of tower - Note: it seemed more efficient to do two methods here, rather
     than one that makes both the Tildes and the Windows, that way the bottom row can simply be a method
     call, rather than a duplication of the code for a row of tildes.
     */
    public static void towerMidTildes() {
    	int towerMidSpaces = SIZE * 4;
    	int towerMidTildes = SIZE * 2 + 3;
    	for (int i = 1; i <= towerMidSpaces; i++) {
    		System.out.print(" ");
    	}
    	for (int j = 1; j <= towerMidTildes; j++) {
    		System.out.print("~");
    	}
    	System.out.println();
    }
    
    // Print "windows" for middle of tower
    public static void towerMidWindows() {
    	int towerMidSpaces = SIZE * 4;
    	for (int i = 1; i <= towerMidSpaces; i++) {
    		System.out.print(" ");
    	}
    	System.out.print("|");
    	for (int i = 1; i <= SIZE; i++) {
    		System.out.print("-O");
    	}
    	System.out.print("-|");
    	System.out.println();
    }
    
    // Print the top of the base at the bottom of the tower
    public static void towerBottomTopOfBase() {
    	int numberLayers = 1 + (SIZE / 2);
    	int topLayerSpaces = 3 * (SIZE / 2);
    	int topLayerMiddleRepetitions =  SIZE * 5 - 3 * (SIZE / 2);
    	for (int i = 0; i < numberLayers; i++) {
    		for (int j = 0; j < topLayerSpaces; j++) {
    			System.out.print(" ");
    		}
    		System.out.print("/");
    		for (int j = 0; j < topLayerMiddleRepetitions; j++) {
    			System.out.print("\"'");
    		}
    		System.out.print("\"\\");
    		System.out.println();
    		topLayerSpaces -= 3;
    		topLayerMiddleRepetitions += 3;
    	}
    }
    
    // Print "windows" for bottom of tower
    public static void towerBottomWindows() {
    	int numberOfWindows = SIZE * 5;
    	for (int i = 1; i <= SIZE; i++) {
    		System.out.print("/");
    		for (int j = 1; j <= numberOfWindows; j++) {
    			System.out.print("\"O");
    		}
    		System.out.print("\"\\");
    		System.out.println();
    	}
    }
    
}