import java.util.Scanner;

/**
 * CS312 Assignment 4.
 * 
 * On my honor, Matthew S. Planchard, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Rock Paper Scissors
 *
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 */

public class RockPaperScissors {
	
	/* A program to allow a human player to play rock - paper - scissors
	 * against the computer. If args.length != 0 then we assume
	 * the first element of args can be converted to an int
	 */
	
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;
	
	
	public static void main(String[] args) {
		RandomPlayer computerPlayer = buildRandomPlayer(args);
		
		Scanner console = new Scanner(System.in);
		
		String name = askName(console);
		int rounds = askRounds(name, console);
		play(name, rounds, computerPlayer, console);
	}


	/*
	 * Build the random player. If args is length 0 then the 
	 * default RandomPlayer is built that follows a predictable 
	 * sequence. If args.length > 0 then we assume we can
	 * convert the first element to an int and build the 
	 * RandomPlayer with that initial value.
	 */
	public static RandomPlayer buildRandomPlayer(String[] args) {
		if(args.length == 0) {
			return new RandomPlayer();
		} else {
			int seed = Integer.parseInt(args[0]);
			return new RandomPlayer(seed);
		}
	}
	
	// Request player name and return as string
	public static String askName(Scanner console) {
		System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
		System.out.print("Please type in your name and press return: ");
		String name = console.nextLine();
		System.out.println();
		System.out.println("Welcome " + name + ".");
		System.out.println();
		return name;
	}
	
	// Request number of rounds and return as int
	public static int askRounds(String name, Scanner console) {
		System.out.println("All right " + name + ". How many rounds would you like to play?");
		System.out.print("Enter the number of rounds you want to play and press return: ");
		final int rounds = console.nextInt();
		console.nextLine();
		System.out.println();
		return rounds;
	}
	
	// Plays the number of rounds of RPS requested by the player. Tracks wins and losses and passes them to the
	// displayResults method
	public static void play(String name, int rounds, RandomPlayer computerPlayer, Scanner console) {
		int computerWins = 0;
		int playerWins = 0;
		
		for (int i = 0; i < rounds; i++) {
			System.out.println("Round " + (i + 1) + ".");
			System.out.println(name + ", please enter your choice for this round.");
			System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
			int playerChoice = console.nextInt();
			console.nextLine();
			int computerChoice = computerPlayer.getComputerChoice();
			System.out.println("Computer picked " + numbersToWords(computerChoice) + ", " + 
						name + " picked " + numbersToWords(playerChoice) + ".");
			System.out.println();
			if (playerChoice == computerChoice) {
				System.out.print("We picked the same thing! This round is a draw.");
			} else if ((playerChoice == ROCK || computerChoice == ROCK) && (playerChoice == PAPER 
						|| computerChoice == PAPER)) {
				System.out.print("PAPER covers ROCK. ");
				
			} else if ((playerChoice == PAPER || computerChoice == PAPER) && (playerChoice == SCISSORS 
					|| computerChoice == SCISSORS)) {
				System.out.print("SCISSORS cut PAPER. ");
			} else {
				System.out.print("ROCK breaks SCISSORS. ");
			}
			if (playerChoice != computerChoice && ((playerChoice == ROCK && computerChoice == PAPER) 
						|| (playerChoice == PAPER && computerChoice == SCISSORS) || (playerChoice == SCISSORS 
						&& computerChoice == ROCK))) {
				System.out.print("I win.");
				computerWins++;
			} else if (playerChoice != computerChoice) {
				System.out.print("You win.");
				playerWins++;
			}
			System.out.println();
			System.out.println();
		}
		System.out.println();
		displayResults(name, rounds, computerWins, playerWins);
	}
	
	// Simple method to convert int playerChoice to the word ROCK, PAPER, or SCISSORS.
	public static String numbersToWords(int choice) {
		if (choice == 1) {
			return "ROCK";
		} else if (choice == 2){
			return "PAPER";
		} else {
			return "SCISSORS";
		}
	}
	
	// Method to display the overall results of any number of rounds of RPS. Automatically passed parameters of
	// player name, number of rounds, computer wins, and player wins
	public static void displayResults(String name, int rounds, int computerWins, int playerWins) {
		
		System.out.println("Number of games of ROCK PAPER SCISSORS: " + rounds);
		System.out.println("Number of times Computer won: " + computerWins);
		System.out.println("Number of times " + name + " won: " + playerWins);
		System.out.println("Number of draws: " + (rounds - computerWins - playerWins));
		
		// Determine whether player or computer is better over all rounds, or if it's a draw
		if (playerWins > computerWins) {
			System.out.println("You, " + name + ", are a master at ROCK, PAPER, SCISSORS.");
		} else if (playerWins < computerWins) {
			System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
			
		} else {
			System.out.println("We are evenly matched.");
		}
	}
}
