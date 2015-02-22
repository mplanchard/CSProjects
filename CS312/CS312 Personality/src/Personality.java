/**
 * CS312 Assignment 8.
 *
 * On my honor, Matthew Planchard, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Personality {

    public static final int NUMBER_QUESTIONS = 70;
    public static final int IS_A = 0;
    public static final int IS_B = 1;
    public static final int IS_DASH = 2;
    public static final int NO_ANSWERS = -1;

    // The main method to process the data from the personality tests. Requests that the user provide the file name.
    // Can analyze without printing, if desired, by removing the printResults method.
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); // do not make any other Scanners connected to System.in
        Scanner fileScanner = getFileScanner(keyboard);
        printResults(analyzePersonalities(fileScanner));
        fileScanner.close();
        keyboard.close();
    }

    // Main method to analyze personalities. Requires the scanner hooked up to the file.
    // Makes an array of all the data, then passes it to a method to do calculations.
    public static String[][] analyzePersonalities(Scanner fileScanner) {
        final int numRecords = fileScanner.nextInt();
        fileScanner.nextLine();
        String[][] namesAndInput = new String[numRecords][2];
        int[][] percentagesB = new int[numRecords][];
        for (int i = 0; i < numRecords; i++) {
            namesAndInput[i][0] = fileScanner.nextLine();
            namesAndInput[i][1] = fileScanner.nextLine();
            percentagesB[i] = calculateCharacteristics(namesAndInput[i][1]);
        }
        return makeResultsArray(namesAndInput, percentagesB, numRecords);
    }

    // Takes the string of A, B, and non-answers and converts to an array containing the %B of intExt, senseIntuit,
    // thinkFeel, and judgePercept answers.
    public static int[] calculateCharacteristics(String input) {
        char[] letters = input.toCharArray();
        int[][] totals = new int[4][3]; // total answers of a, b, or dash (columns) for I/E, S/N, T/F, and J/P (rows)
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < NUMBER_QUESTIONS; j += 7)
                totals[(i + 1) / 2][calcType(letters[j])]++;
        }
        return calculatePercentages(totals);
    }

    // Tests characters to determine if they are A's, B's, or dashes. Ignores case.
    // Returns arbitrary values used in other methods.
    public static int calcType(char letter) {
        String testString = ("" + letter);
        if (testString.equalsIgnoreCase("A"))
            return IS_A;
        else if (testString.equalsIgnoreCase("B"))
            return IS_B;
        else return IS_DASH;
    }

    // Calculates percentages and returns an array with %B of intExt, senseIntuit, thinkFeel, and judgePercept, in that order.
    // Takes as a parameter an array totals, which is a two dimensional array containing intExt, senseIntuit, thinkFeel, and judgePercept
    // as rows and number of a, b, and non-answers as columns.
    public static int[] calculatePercentages(int totals[][]) {
        int[] percentagesB = new int[4];
        for (int i = 0; i < 4; i++) {
            int total = totals[i][0] + totals[i][1];
            if (total == 0)
                percentagesB[i] = NO_ANSWERS;
            else percentagesB[i] = (int) (totals[i][1] / (total * 1.0) * 100 + 0.5);
        }
        return percentagesB;
    }

    // Generate a results array containing all of the results as they will be printed, in unformatted form, as a two
    // dimensional array where each row is for a given record, and the columns correspond to the output.
    // Requires the namesAndInput array, the percentages array, and the total number of records.
    public static String[][] makeResultsArray(String namesAndInput[][], int percentagesB[][], int numRecords) {
        String[][] resultsArray = new String[numRecords][6];
        for (int i = 0; i < numRecords; i++) {
            resultsArray[i][0] = namesAndInput[i][0];
            for (int j = 0; j < 4; j++) {
                resultsArray[i][j + 1] = String.valueOf(percentagesB[i][j]);
                if (resultsArray[i][j + 1].equals(String.valueOf(NO_ANSWERS)))
                    resultsArray[i][j+1] = "NO ANSWERS";
            }
            resultsArray[i][5] = resultLetters(percentagesB, i);
        }
        return resultsArray;
    }

    // Converts the string of percentages to a series of 4 letters corresponding to personality types. Requires the
    // percentagesB array and the current row number. Assumes the data is in the E/I, S/N, T/F, and J/P order.
    public static String resultLetters(int percentagesB[][], int row) {
        String resultLetters = "";
        String[] letterArray = {"E", "I", "S", "N", "T", "F", "J", "P"};
        for (int i = 0; i < 4; i++)
            resultLetters+= resultLettersTest(letterArray[i * 2], letterArray[i * 2 + 1], percentagesB[row][i]);
        return resultLetters;
    }

    // Converts the numerical results to letters, using the letters provided in the parameters as the "A" letter
    // or the "B" letter. Also requires the number to be converted to be passed as a parameter.
    public static String resultLettersTest(String aLetter, String bLetter, int percent) {
        if (percent == -1)
            return "-";
        else if (percent > 50)
            return bLetter;
        else if (percent < 50)
            return aLetter;
        else return "X";
    }

    // Method to format and print results, given the results array and the number of records.
    public static void printResults(String[][] resultsArray) {
        // Uncomment to base the width of the first column on the maximum name length, rather than a static value.
        // Be sure to use formatString in the printf statement rather than the current string, if you do.
        /* int maxNameLength = 0;
        for (int i = 0; i < numRecords; i ++)
            maxNameLength = Math.max(resultsArray[i][0].length(), maxNameLength);
        String formatString = ("%" + (maxNameLength + 4) + "s%11s%11s%11s%11s%s%s%n");
        */
        for (int i = 0; i < resultsArray.length; i++) {
            System.out.printf("%29s%11s%11s%11s%11s%s%s%n", (resultsArray[i][0] + ":"), resultsArray[i][1], resultsArray[i][2],
                    resultsArray[i][3], resultsArray[i][4], " = ", resultsArray[i][5]);
        }
    }

    // Method to choose a file.
    // Asks user for name of file.
    // If file not found create a Scanner hooked up to a dummy set of data
    // Example use:
    public static Scanner getFileScanner(Scanner keyboard){
        Scanner result = null;
        try {
            System.out.print("Enter the name of the file with the personality data: ");
            String fileName = keyboard.nextLine().trim();
            System.out.println();
            result = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e) {
            System.out.println("Problem creating Scanner: " + e);
            System.out.println("Creating Scanner hooked up to default data " + e);
            String defaultData = "1\nDEFAULT DATA\n"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
            result = new Scanner(defaultData);
        }
        return result;
    }
}