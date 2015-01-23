/**
 * @authors: Student 1:
 *           Student 2:
 * CS312 Assignment 6.
 *
 *  On OUR honor, Sam Valdez and Matthew Planchard, this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *
 * A program to simulate the dice game of Pig and the effectiveness of
 * various player strategies.
 *
 * Student 1: Sam Valdez
 * UTEID: sjv494
 * email address: valdez.samantha.j@gmail.com
 * Section 5 digit ID: 52827
 * Grader name: Donghyuk Shin
 * Number of slip days used on this assignment: 0
 *
 * Student 2: Matthew Planchard
 * UTEID: msp2377
 * email address: msplanchard@gmail.com
 *
 */

import java.util.Random;

public class PigSimulation {

    // number of sides on the die
    public static final int SIDES = 6;

    // constants for strategy based on stopping when a certain number
    // of points reached for the turn
    public static final int SCORE_STOPS_TURN = 1;
    public static final int SCORE_LIMIT_FOR_TURN = 15;

    // constants for strategy based on stopping when a certain number
    // of rolls are made for the turn
    public static final int ROLLS_STOPS_TURN = 2;
    public static final int ROLLS_LIMIT_FOR_TURN = 5;

    // pig 1 output uses 2000316, pig 2 uses 20141015, pig 3 uses 615853
    public static final int SEED_VALUE = 2000316;

    // CS312 students, add your constants here
    public static final int ROLL_AGAIN = 0;
    public static final int HOLD = 1;


    // Show the intro. Then play two games of Pig with 
    // the two given strategies and show the output.
    // Then run various experiments playing 40,000 games of Pig.
    public static void main(String[] args) {
        intro();
        Random randNumGen = createRandomObject(args);
        runSampleGame(randNumGen);
        runExperiments(randNumGen);
    }

    // Method to create random object to generate random numbers. Takes args as parameters. Returns a random integer.
    public static Random createRandomObject(String[] args) {
        if(args == null || args.length == 0)
            return new Random(SEED_VALUE);
        else
            return new Random(Integer.parseInt(args[0]));
    }


    // run sample games with output and the given random number generator. Requires randNumGen.
    public static void runSampleGame(Random randNumGen) {
        System.out.println("RUNNING SAMPLE GAMES WITH OUTPUT");
        System.out.println("***** START OF GAME *****");
        System.out.println();
        if (playGame(randNumGen, SCORE_STOPS_TURN, ROLLS_STOPS_TURN, true))
            System.out.println("player 1 won the game!");
        else System.out.println("player 2 won the game!");
    }


    // run various strategies against each other. Requires randNumGen.
    public static void runExperiments(Random randNumGen) {
        System.out.println();
        System.out.println("RUNNING EXPERIMENTS (10,000 GAMES EACH) WITH NO OUTPUT");
        System.out.println();
        System.out.println("player 1 and player 2 stop at "
                + SCORE_LIMIT_FOR_TURN + " points for turn");
        performExperiment(randNumGen, SCORE_STOPS_TURN , SCORE_STOPS_TURN);
        System.out.println("player 1 and player 2 stop at "
                + ROLLS_LIMIT_FOR_TURN + " rolls for turn");
        performExperiment(randNumGen, ROLLS_STOPS_TURN , ROLLS_STOPS_TURN);
        System.out.println("player 1 stops at " + SCORE_LIMIT_FOR_TURN  +
                " points and player 2 stops at " + ROLLS_LIMIT_FOR_TURN + " rolls");
        performExperiment(randNumGen, SCORE_STOPS_TURN , ROLLS_STOPS_TURN);
        System.out.println("player 1 stops at " + ROLLS_LIMIT_FOR_TURN
                + " rolls and player 2 stops at " + SCORE_LIMIT_FOR_TURN + " points");
        performExperiment(randNumGen, ROLLS_STOPS_TURN , SCORE_STOPS_TURN );
    }

    // Run 10,000 games of pig using the given strategies.
    // After running the games print the number of wins for player 1 and player 2.
    // There is no output for individual games or turns.
    // Parameters are randNumGen, strategy1, and strategy 2
    public static void performExperiment(Random randNumGen, int strategy1, int strategy2) {

        int playerOneWins = 0;
        int playerTwoWins = 0;
        for (int i = 0; i < 10000; i++) {
            if (playGame(randNumGen, strategy1, strategy2, false))
                playerOneWins++;
            else playerTwoWins++;
        }
        System.out.println("player 1 wins: " + playerOneWins);
        System.out.println("player 2 wins: " + playerTwoWins);
        System.out.println();
    }


    // play one game.
    // The method returns true if player 1 wins the game, false if player 2 wins the game.
    // alternate player's taking turns 
    // play until one player exceeds the goal
    // Simply swaps players back and forth after a turn.
    // Requires randNumGen, strategy1, strategy2, and whether or not output should be shown as parameters.
    public static boolean playGame(Random randNumGen, int strategy1, int strategy2, boolean showOutput) {

        int playerOneScore = 0;
        int playerTwoScore = 0;
        int turns = 0;
        while (playerOneScore < 100 && playerTwoScore < 100) {
            turns++;
            if (turns % 2 != 0)
                playerOneScore += gameLoop(randNumGen, strategy1, turns, 1, playerOneScore, playerTwoScore, showOutput);
            else playerTwoScore += gameLoop(randNumGen, strategy2, turns, 2, playerOneScore, playerTwoScore, showOutput);
        }
        if (showOutput) {
            System.out.println("FINAL RESULTS: ");
            System.out.println("Number of turns: " + turns);
            System.out.println("player 1 score: " + playerOneScore);
            System.out.println("player 2 score: " + playerTwoScore);
        }
        return whoWins(playerOneScore, playerTwoScore);
    }

    // The main loop of the game. Calls rollLoop to run a given turn for a given player. Requires randNumGen, strategy for the player, number of
    // turns, player number, player scores, and show output as parameters.
    public static int gameLoop(Random randNumGen, int strategy, int turns, int player, int playerOneScore,
                               int playerTwoScore, boolean showOutput) {
        if (showOutput) {
            System.out.println("START OF TURN NUMBER " + turns);
            System.out.println("It is player " + player + "'s turn.");
        }
        return rollLoop(playerOneScore, playerTwoScore, player, randNumGen, showOutput, strategy);
    }

    // Simple method to determine the winner based on playerOneScore and playerTwoScore, which are passed as parameters.
    public static boolean whoWins(int playerOneScore, int playerTwoScore) {
        return playerOneScore > playerTwoScore;
    }

    // Simple method to return a random number between 1 and 6.
    public static int roll(Random randNumGen) {
        return randNumGen.nextInt(SIDES) + 1;
    }

    // The meat and potatoes of the game. Does rolls, adds the scores, stops the rolls when the strategy condition is met. Requires player scores, player
    // number, the random number generator, show output, and the strategy as parameters.
    public static int rollLoop (int playerOneScore, int playerTwoScore, int player, Random randNumGen, boolean showOutput, int strategy) {
        int tempOneScore = playerOneScore;
        int tempTwoScore = playerTwoScore;
        int numberRolls = 0;
        int roll = 0;
        int score = 0;
        int scoreOrRolls = numberRolls;
        int limit = ROLLS_LIMIT_FOR_TURN;
        if (strategy == SCORE_STOPS_TURN) {
            scoreOrRolls = score;
            limit = SCORE_LIMIT_FOR_TURN;
        }
        return rollWhileLoop(roll, scoreOrRolls, limit, tempOneScore, tempTwoScore, showOutput, player, numberRolls, score, strategy, randNumGen);
    }

    // A loop to do the rolls while the limit of the current strategy method remains above the current score or number of rolls.
    // Takes as parameters basically everything, but they're automatically called, so it's cool.
    public static int rollWhileLoop(int roll, int scoreOrRolls, int limit, int tempOneScore, int tempTwoScore, boolean showOutput,
                                    int player, int numberRolls, int score, int strategy, Random randNumGen) {
        while (roll != 1 && scoreOrRolls < limit && tempOneScore < 100 && tempTwoScore < 100) {
            roll = roll(randNumGen);
            score += roll;
            numberRolls++;
            int decision = ROLL_AGAIN;
            if (strategy == SCORE_STOPS_TURN)
                scoreOrRolls = score;
            else scoreOrRolls = numberRolls;
            if (roll == 1) {
                score = 0;
                if (showOutput)
                    pigOutput(player, numberRolls);
            } else {
                if (player == 1)
                    tempOneScore += roll;
                else tempTwoScore += roll;
                if (scoreOrRolls >= limit || tempOneScore >= 100 || tempTwoScore >= 100)
                    decision = HOLD;
                if (showOutput)
                    printOutput(player, roll, numberRolls, score, tempOneScore, tempTwoScore, decision);
            }
        }
        return score;
    }

    // Method to print output for a given roll. Requires player, roll, numberRolls, score, tempOneScore, tempTwoScore, and the decision as
    // parameters. Is automatically called by the rollLoop method.
    public static void printOutput(int player, int roll, int numberRolls, int score, int tempOneScore, int tempTwoScore, int decision) {
        System.out.println("player " + player + " rolled a " + roll + " on roll number " + numberRolls);
        System.out.println();
        System.out.println("Number of rolls this turn: " + numberRolls);
        System.out.println("Score for turn so far: " + score);
        System.out.println("If you HOLD now scores will be: ");
        System.out.println("player 1: " + tempOneScore);
        System.out.println("player 2: " + tempTwoScore);
        System.out.println();
        if (decision == HOLD) {
            System.out.println("DECISION - HOLD - TURN IS OVER");
        } else System.out.println("DECISION - ROLL AGAIN");
        System.out.println();
    }

    // Method to show output if the player rolls a one. Requires the player number, the roll, and the number of Rolls.
    public static void pigOutput(int player, int numberRolls) {
        System.out.println("player " + player + " rolled a 1 on roll number " + numberRolls);
        System.out.println();
        System.out.println("PIG! Score for turn is 0. TURN IS OVER");
        System.out.println();
    }


    // print out the intro to the game
    public static void intro() {
        System.out.println("Welcome to the dice game Pig.");
        System.out.println("The game is played between two players.");
        System.out.println("Players alternate taking turns.");
        System.out.println();
        System.out.println("During a turn a player rolls a six sided die.");
        System.out.println("If the result of the roll is a 1 the player's score");
        System.out.println("for the turn is 0 and it is the next player's turn.");
        System.out.println("If the result of the die is not a 1 the result is added");
        System.out.println("to the player's score for the turn.");
        System.out.println();
        System.out.println("After the first roll of a turn a player must decide to");
        System.out.println("HOLD or ROLL AGAIN. If a player HOLDs their score");
        System.out.println("for the turn is added to their total score.");
        System.out.println("if a player ROLLs AGAIN they roll the die again.");
        System.out.println();
        System.out.println("The same rules apply to the second and subsequent");
        System.out.println("rolls in the turn: a 1 reduces the player's");
        System.out.println("score for the turn to 0 and the turn is over.");
        System.out.println("If the die is not 1 the result is added to the");
        System.out.println("player's score for the turn and they must decide");
        System.out.println("to HOLD or ROLL AGAIN.");
        System.out.println();
        System.out.println("The first player to reach a total of 100 wins the game.");
        System.out.println();
        System.out.println("This program simulates the results of different");
        System.out.println("strategies for the game.");
        System.out.println();
    }

    // CS312 Students, questions to answer:
    
    /*
    1. Change the initial seed for the random number generator. 
    Are the new results of the experiments similar to the results with the given 
    seed of 2000316?

        Results for various strategies using initial seed of 2000316:
            player 1 and player 2 stop at 15 points for turn
            player 1 wins: 5366
            player 2 wins: 4634

            player 1 and player 2 stop at 5 rolls for turn
            player 1 wins: 5376
            player 2 wins: 4624

            player 1 stops at 15 points and player 2 stops at 5 rolls
            player 1 wins: 5049
            player 2 wins: 4951

            player 1 stops at 5 rolls and player 2 stops at 15 points
            player 1 wins: 5725
            player 2 wins: 4275

         Results for various strategies using new seed of 69 (lol):

            player 1 and player 2 stop at 15 points for turn
            player 1 wins: 5466
            player 2 wins: 4534

            player 1 and player 2 stop at 5 rolls for turn
            player 1 wins: 5371
            player 2 wins: 4629

            player 1 stops at 15 points and player 2 stops at 5 rolls
            player 1 wins: 4987
            player 2 wins: 5013

            player 1 stops at 5 rolls and player 2 stops at 15 points
            player 1 wins: 5702
            player 2 wins: 4298

       It seems like the results are fundamentally similar. There is a slight difference using the third distribution of
       strategies, probably just due to small random variation, since player 1 lost only by a slim margin.

    2. When the players use the same strategy, is going first an advantage?

        When the players use the same strategy, yes, going first seems to confer a substantial advantage, given that,
        over the test span of 10,000 games, player one consistently bested player two by over a 15% margin. This advantage
        was maintained for each of the random seeds tested, regardless of which strategy was being used.

    3. Which is better, the stop at 15 points or stop at 5 rolls strategy?

        It seems as though the five rolls strategy is superior. Although the advantage conferred by going first seems to
        trump strategical concerns, the margin of victory was substantially smaller when the first player was using the 15
        points strategy, while the second player was using the 5 rolls strategy. In addition, whenever the first player was
        using the 5 rolls strategy, while the second player used the 15 points strategy, the margin of victory was generally
        immense.

    4. Try different values for the number of points required to stop for a turn and 
    the number of rolls necessary to stop for a turn. Are 15 points and 5 rolls the
    most effective values for those strategies?

        It seems as though 5 and 15 are not necessarily the most effective values for those strategies. However, more detailed testing
        and possibly lots of math would be required to determine the ideal values. When we tested with 7 rolls for example, it seemed
        to confer a significant advantage, to the extent that player 2 was able to beat player 1, in spite of player 1's advantage
        due to going first. Other numbers of rolls were less effective, getting continually less effective the further away from
        5-7 we went. Score values had less of an effect, except when extremely small or larger than 30, in both of which cases
        the player using the score strategy was at a major disadvantage.

    5. Describe an alternative strategy. Something different than stopping at a fixed score
    or number of rolls for a turn.

        You could check your horoscope at the beginning of the day, and if your horoscope is positive,
        stop at three rolls, unless Cancer is in Virgo, in which case you could wait until your score was equal to
        a factor of the distance between Earth and the Moon. If your horoscope is negative, hide and don't play
        Pig that day.

        Alternately, you could switch between point and roll limits for each turn. You could also switch strategies from
        point limit to roll limit as you begin to approach the overall score limit of 100, in order to minimize your chances
        of getting a pig.

    */

}