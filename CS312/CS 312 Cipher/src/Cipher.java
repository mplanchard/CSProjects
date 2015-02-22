import java.util.Scanner;

/**
 * CS312 Assignment 5.
 * 
 * On my honor, Matthew S. Planchard, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to encrypt and decrypt messages using a columnar transposition cipher.
 *
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 */

public class Cipher {

    // CS312 Students: This constant must be set to 10 in the
    // final version of your program that your turn in.
    public static final int MAX_ROWS = 10;

    // main method to demonstrate various encryptions and
    // decryptions using a columnar transposition cipher
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); 
        showIntro();
        encrypt(keyboard);
        decrypt(keyboard);        
        
        keyboard.close();
    }


    // show the introduction to the program
    public static void showIntro() {
        System.out.println("This program demonstrates a transposition cipher.");
        System.out.println("A cipher is an algorithm to encrypt or decrypt a message.");
        System.out.println();
        System.out.println("This program will demonstrate encrypting a message with");
        System.out.println("a columnar transposition cipher both with and without");
        System.out.println("padding characters. The program will then decrypt a message");
        System.out.println("assuming it was encrypted with a columnar transposition cipher");
        System.out.println("with padding.");
        System.out.println("After accepting user input, the program displays some tests.");
        System.out.println();
        System.out.println();
    }
    
    // Calls for a string and encrypts with no padding and then with padding using 2 to MAX_ROWS rows.
    public static void encrypt(Scanner keyboard) {
    	System.out.println("A demonstration of encrypting with a columnar transposition cipher:");
    	System.out.println();
    	String clearText = getText("Enter the message to encrypt: ", keyboard);
    	System.out.println();
    	System.out.println("Message encrypted with columnar transposition cipher and no padding.");
    	cipher(clearText, false);
    	System.out.println("Message encrypted with columnar transposition cipher and padding.");
    	System.out.println();
    	cipher(clearText, true);
    }
    
    // Calls for a string and decrypts with 2 and up numbers of rows (up to MAX_ROWS)
    public static void decrypt(Scanner keyboard) {
    	System.out.println("A demonstration of decrypting with a columnar transposition cipher:");
    	System.out.println("If the length of the message is not a multiple of the number of rows");
    	System.out.println("it will be padded which may throw off the decryption.");
    	System.out.println();
    	String encryptedText = getText("Enter the message to decrypt: ", keyboard);
    	System.out.println();
    	decipher(encryptedText);
    }
    
    // Uses the generateCipher method to create either a padded or a non-padded cipher. Handles
    // the addition of padding to the string via the addPad method. Requires a string and a boolean corresponding
    // to whether or not the cipher should be padded.
    public static void cipher(String clearText, Boolean isPadded) {
    	for (int rows = 2; rows <= MAX_ROWS; rows++) {
    		// Add padding if necessary
    		if (isPadded) {
    			System.out.println("Clear text padded for " + rows + " rows: " + addPad(rows, clearText));
    			System.out.println(generateCipher(rows, (addPad(rows, clearText))));
    			System.out.println();
    		}
    		else {
                System.out.println(generateCipher(rows, clearText));
            }
    	}
    	System.out.println();
    }
    
    // Method to attempt to decipher a string of text using columnar ciphers from 2 through MAX_ROWS columns.
    // Calls the addPad method, which pads the string if it is not a multiple of the number of rows
    public static void decipher(String encryptedText) {
    	System.out.println("Messages Decrypted with a Columnar Transposition Cipher");
    	for (int rows = 2; rows <= MAX_ROWS; rows++) {
    		
    		String tempString = addPad(rows, encryptedText);
    		
    		System.out.println();
    		System.out.println("Decrypted text padded for " + rows + " rows: " + tempString);
    		System.out.print("Decrypted with " + rows + " rows: ");
    		
    		String decipheredText = "";
    		for (int startChar = 0; startChar < tempString.length() / rows; startChar++) {
    			decipheredText += tempString.charAt(startChar);
    			int currentChar = startChar + tempString.length() / rows;
    			while (currentChar < tempString.length()) {
    				decipheredText += tempString.charAt(currentChar);
    				currentChar += (tempString.length() / rows);
    			}
    		}
    		System.out.println(decipheredText);
    	}
    }
    
    // Method to generate a columnar cipher when the number of rows and a string of text are passed 
    public static String generateCipher(int numRows, String clearText) {
    	String cipher = ("Encrypted with " + numRows + " rows: ");
    	for (int i = 0; i < numRows; i++) {
    		int currentChar = i;
    		while (currentChar < clearText.length()) {
    			cipher += clearText.charAt(currentChar);
    			currentChar += numRows;
    		}
    	}
    	return cipher;
    }
    
    // Method to get text from the user, prints the preamble string parameter and returns the next line of input.
    public static String getText(String preamble, Scanner keyboard) {
    	System.out.print(preamble);
    	return keyboard.nextLine();
    }
    
    // Method to add X's to a string as padding given the number of rows in the cipher. 
    // Returns a new string with any (or no) additional X's as required.
    public static String addPad(int rows, String text) {
    	String addX = "";
		if (text.length() % rows != 0) {
			int numColumns = text.length() / rows + 1;
			int numX = numColumns * rows - text.length();
			for (int i = 0; i < numX; i++) {
				addX += "X";
			}
		}
		return text + addX;
    }
    
    
}