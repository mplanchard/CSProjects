import java.util.Scanner;

/**
 * CS312 Assignment 9.
 *
 * On my honor, Matthew S. Planchard, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Section 5 digit ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 *
 * Program to decrypt a message that has been
 * encrypted with a substitution cipher.
 * We assume only characters with ASCII codes
 * from 32 to 126 inclusive have been encrypted.
 */

public class Decrypt {

    static final int BEGIN_CHARS = 32;
    static final int END_CHARS = 127;


    // Main method to decrypt a substitution cipher. Pulls the file and converts it to a string for
    // decryption.
    public static void main(String[] arg) {
        Scanner keyboard = new Scanner(System.in);
        String fileName = getFileName(keyboard);
        String encryptedText = DecryptUtilities.convertFileToString(fileName);
        printString(encryptedText, true, "current");
        decryptionLoop(encryptedText, keyboard);
        keyboard.close();
    }

    // Main decryption method. Calls methods to generate a decryption key and to generate a decrypted string.
    // Loops until the user is satisfied
    // Requires the encrypted text as a String parameter and the scanner.
    public static void decryptionLoop(String encryptedText, Scanner keyboard) {
        boolean loop = true;
        char[] decryptionKey = DecryptUtilities.getDecryptionKey(generateFrequencyArray(encryptedText, true));
        printDecryptionKey(decryptionKey);
        String decryptedText = generateDecryptedText(encryptedText, decryptionKey);
        while (loop) {
            printString(decryptedText, false, "current");
            loop = changeKey(decryptionKey, keyboard);
            decryptedText = generateDecryptedText(encryptedText, decryptionKey);
        }
        System.out.println();
        printDecryptionKey(decryptionKey);
        printString(decryptedText, false, "final");
    }

    // Generates a frequency array given a string of text, in which character frequency is
    // mapped to the array element corresponding to that character's ASCII value.
    // Parameters are the string to be analyzed and a Boolean indicating whether or not the
    // array should be printed.
    public static int[] generateFrequencyArray(String text, Boolean print) {
        int[] frequencyArray = new int[128];
        for (int i = 0; i < text.length(); i++)
            frequencyArray[text.charAt(i)]++;
        if (print) printFrequencyArray(frequencyArray);
        return frequencyArray;
    }

    // Generates a decrypted string based on the encrypted text and the decryption key
    public static String generateDecryptedText(String encryptedText, char[] decryptionKey) {
        String decryptedText = "";
        for (int i = 0; i < encryptedText.length(); i++)
            decryptedText += decryptionKey[encryptedText.charAt(i)];
        return decryptedText;
    }

    // Asks if the user wants to change the key. Returns false if they do not.
    // Uses the swapElements method to change the array as requested by the user.
    // Requires the array to be altered and the keyboard to be passed as parameters
    public static Boolean changeKey(char[] decryptionKey, Scanner keyboard) {
        System.out.println("Do you want to make a change to the key?");
        System.out.print("Enter 'Y' or 'y' to make change: ");
        if (keyboard.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter the decrypt character you want to change: ");
            char changeFrom = keyboard.nextLine().charAt(0);
            System.out.print("Enter what the character " + changeFrom + " should decrypt to instead: ");
            char changeTo = keyboard.nextLine().charAt(0);
            System.out.println(changeFrom + "'s will now decrypt to " + changeTo + "'s and vice versa.");
            System.out.println();
            swapElements(decryptionKey, changeFrom, changeTo);
            return true;
        }
        else return false;
    }

    // Method to display encrypted/decrypted text. Parameters are the text to be displayed and
    // a boolean representing whether or not the text is encrypted. Also requires whether the
    // version of the text is current or final.
    public static void printString(String text, Boolean encrypted, String currentOrFinal) {
        if (encrypted)
            System.out.println("The encrypted text is:");
        else {
            System.out.println("The " + currentOrFinal + " version of the decrypted text is: ");
            System.out.println();
        }
        System.out.println(text);
    }

    // Method to nicely print a frequency array when passed the frequency array.
    public static void printFrequencyArray(int[] frequencyArray) {
        System.out.println("Frequencies of characters.");
        System.out.println("Character - Frequency");
        for (int i = BEGIN_CHARS; i < END_CHARS; i++)
            System.out.println((char) i + " - " + frequencyArray[i]);
        System.out.println();
    }

    // Method to nicely print the decryption key when passed a char array of the decryption key.
    public static void printDecryptionKey(char[] decryptionKey) {
        System.out.println("The current version of the key for ASCII characters 32 to 126 is: ");
        for (int i = BEGIN_CHARS; i < END_CHARS; i++)
            System.out.println("Encrypt character: " + (char) i + ", decrypt character: " + decryptionKey[i]);
        System.out.println();
    }

    // Method to swap character elements in an array.
    // Parameters are the array and the two elements to be swapped.
    // Doesn't return an array; makes changes to the original.
    public static void swapElements(char[] charArray, char charOne, char charTwo) {
        for (int i = BEGIN_CHARS; i < END_CHARS; i++) {
            if (charArray[i] == charOne)
                charArray[i] = charTwo;
            else if (charArray[i] == charTwo)
                charArray[i] = charOne;
        }
    }

    // get the name of file to use
    public static String getFileName(Scanner kbScanner) {
        System.out.print("Enter the name of the encrypted file: ");
        String fileName = kbScanner.nextLine().trim();
        System.out.println();
        return fileName;
    }
}