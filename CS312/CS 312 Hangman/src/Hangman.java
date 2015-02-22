/**
 * CS312 Assignment 6.
 *
 * On my honor, Matthew S. Planchard, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 */

import java.util.Scanner;

public class Hangman {

    /* A program to play a game of hangman! Picks a secret phrase and allows the user to choose letters.
     * When all of the correct letters have been picked or the maximum number of incorrect guesses is reached,
     * the game ends, asking if the player would like to play again.
     * The default number of allowed incorrect guesses is 5. To change it, change the MAX_GUESSES variable
     * in the main method.
     */
    public static void main(String[] args) {
        PhraseBank phrases = buildPhraseBank(args);
        final int MAX_GUESSES = 5;
        Scanner keyboard = new Scanner(System.in);
        intro(MAX_GUESSES);
        startGame(phrases, MAX_GUESSES, keyboard);
        keyboard.close();
    }

    /*
    Build the PhraseBank.
    If args is empty or null, build the default phrase bank.
    If args is not null and has a length greater than 0
    then the first elements is assumed to be the name of the
    file to build the PhraseBank from.
    */
    public static PhraseBank buildPhraseBank(String[] args) {
        PhraseBank result;
        if(args == null || args.length == 0
                || args[0] == null || args[0].length() == 0)
            result =  new PhraseBank();
        else
            result = new PhraseBank(args[0]);
        return result;
    }

    // show the intro to the program
    public static void intro(int MAX_GUESSES) {
        System.out.println("This program plays the game of hangman.");
        System.out.println();
        System.out.println("The computer will pick a random phrase.");
        System.out.println("Enter capital letters as your guesses.");
        System.out.println("After " + MAX_GUESSES + " wrong guesses you lose.");
        System.out.println();
    }

    /*
    The main playHangman method. Uses the hangmanLoop method to Pull a secret phrase from the PhraseBank,
    convert it to a secret phrase with asterisks, get user input, check user input, update the list of
    allowable letters, and update the number of incorrect guesses. Ends when the user has guessed the
    entire word or when the user has reached MAX_GUESSES incorrect guesses. Parameters are the phraseBank,
    MAX_GUESSES, and the scanner. Calls playAgain to determine whether another game should be played.
    */
    public static void startGame(PhraseBank phrases, int MAX_GUESSES, Scanner keyboard) {
        System.out.println("I am thinking of a " + phrases.getTopic() + " ...");
        System.out.println();
        playHangman(phrases, MAX_GUESSES, keyboard);
        playAgain(phrases, MAX_GUESSES, keyboard);
    }


    // Initiates a hangman game. Creates a secret phrase using convertToAsterisks and generates allowable letters
    // with getLetters. Calls hangmanLoop for the main portion of the game.
    // Parameters are the PhraseBank, MAX_GUESSES, and the scanner.
    public static void playHangman(PhraseBank phrases, int MAX_GUESSES, Scanner keyboard) {
        String phrase = phrases.getNextPhrase();
        String secretPhrase = convertToAsterisks(phrase);
        String letters = getLetters();
        hangmanLoop(phrase, secretPhrase, letters, MAX_GUESSES, keyboard);
    }

    // The main hangman loop. Continues as long as incorrect guesses are less than MAX_GUESSES or until the
    // user has guessed the phrase. Parameters are the phrase, the converted secret phrase, the list of allowable letters,
    // the MAX_GUESSES, and the scanner.
    public static void hangmanLoop(String phrase, String secretPhrase, String letters, int MAX_GUESSES, Scanner keyboard) {
        int incorrectGuesses = 0;
        while (incorrectGuesses < MAX_GUESSES && secretPhrase.contains("*")) {
            System.out.println("The current phrase is " + secretPhrase);
            String input = getInput(letters, keyboard);
            System.out.println();
            System.out.println("You guessed " + input + ".");
            if (phrase.contains(input)) {
                System.out.println("That is present in the secret phrase.");
                secretPhrase = replaceAsterisks(input, phrase, secretPhrase);
            } else {
                System.out.println("That is not present in the secret phrase.");
                incorrectGuesses++;
            }
            letters = letters.replace(input + " ", "");
            System.out.println("Number of wrong guesses so far: " + incorrectGuesses);
        }
        winOrLose(incorrectGuesses, phrase);
    }

    // Determines whether the user has won or lost a game of Hangman. Parameters are the number of incorrect guesses,
    // the secret phrase, and the original phrase.
    public static void winOrLose(int incorrectGuesses, String phrase) {
        if (incorrectGuesses < 5) {
            System.out.println("The phrase is " + phrase + ".");
            System.out.println("You win!!");
        } else System.out.println("You lose. The secret phrase was " + phrase);
    }

    // Determines whether to start a new game of Hangman. If the user enters anything other than Y or y, the program
    // terminates. Parameters are the PhraseBank, MAX_GUESSES, and the Scanner.
    public static void playAgain(PhraseBank phrases, int MAX_GUESSES, Scanner keyboard) {
        System.out.println("Do you want to play again?");
        System.out.print("Enter 'Y' or 'y' to play again: ");
        String input = keyboard.nextLine();
        System.out.println();
        if (input.equalsIgnoreCase("Y")) {
            startGame(phrases, MAX_GUESSES, keyboard);
        }
    }

    // Simple method to get a user's guess. Prints the current allowable letters and returns the user's input.
    // Parameters are allowable letters and the Scanner.
    public static String getInput(String letters, Scanner keyboard) {
        System.out.println("The letters you have not guessed yet are: ");
        System.out.println(letters);
        System.out.print("Enter your next guess: ");
        String input = keyboard.nextLine();
        return checkInput(input.toUpperCase(), letters, keyboard);
    }

    // Checks user input to ensure that it is not empty, is a letter, that it is one of the allowable letters, and that it
    // is no more than 1 character long.
    // Parameters are the input to be checked, the allowable letters, and the Scanner.
    public static String checkInput(String input, String letters, Scanner keyboard) {
        while (input.isEmpty() || !Character.isLetter(input.charAt(0)) || !letters.contains(input) || input.length() > 1) {
            System.out.println();
            System.out.println(input + " is not a valid guess.");
            input = getInput(letters, keyboard);
        }
        return input;
    }

    // Simple method to convert a title into asterisks and underscores.
    public static String convertToAsterisks(String phrase) {
        String secretPhrase = "";
        if (phrase.contains("_")) {
            int spaceIndex = phrase.indexOf("_");
            for (int i = 0; i < spaceIndex; i++)
                secretPhrase += "*";
            secretPhrase += "_";
            while (phrase.indexOf("_", spaceIndex + 1) != -1) {
                for (int i = 1; i < phrase.indexOf("_", spaceIndex + 1) - spaceIndex; i++)
                    secretPhrase += "*";
                secretPhrase += "_";
                spaceIndex = phrase.indexOf("_", spaceIndex + 1);
            }
            for (int i = 1; i < phrase.length() - spaceIndex; i++)
                secretPhrase += "*";
        } else for (int i = 0; i < phrase.length(); i++)
            secretPhrase += "*";
        return secretPhrase;
    }

    // Method to replace the asterisks in a secret phrase with the user's input
    public static String replaceAsterisks(String input, String phrase, String secretPhrase) {
        int index = phrase.indexOf(input);
        secretPhrase = (secretPhrase.substring(0, index) + input + secretPhrase.substring(index + 1));
        while (phrase.indexOf(input, index + 1) != -1) {
            index = phrase.indexOf(input, index + 1);
            secretPhrase = (secretPhrase.substring(0, index) + input + secretPhrase.substring(index + 1));
        }
        return secretPhrase;
    }

    // Simple method to generate the alphabet to prompt the user.
    public static String getLetters() {
        String letters = "";
        for (int i = 65; i <= 90; i++) {
            letters += (char) i;
            letters += " ";
        }
        return letters;
    }
}