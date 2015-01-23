import java.util.Scanner;

/**
 * CS312 Assignment 10.
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
 * Program that allows two people to play Connect Four.
 */

public class ConnectFour {

    public static final int EMPTY = 0;
    public static final int RED = 1;
    public static final int BLACK = -1;
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;

    // Main method to implement a game of Connect Four
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        intro();
        gameLoop(key);
        key.close();
    }

    // Main game loop. Loops until a player wins.
    public static void gameLoop(Scanner key) {
        String[] players = getPlayers(key);
        int[][] board = new int[ROWS][COLUMNS];
        displayBoard(board, false);
        boolean win = false;
        int turn = 0;
        while (!win) {
            int playerPieceNum =  turn % 2 * -1 + 1 - (turn %2);
            System.out.println(players[turn % 2] + " it is your turn.");
            System.out.println("Your pieces are the " + pieceType(playerPieceNum) + "'s.");
            dropChecker(board, playerPieceNum, turn, key, players);
            win = checkWin(board, players[turn % 2]);
            System.out.println();
            displayBoard(board, win);
            turn++;
        }
    }

    // Prompt for player names and store as an array, player 1 at index 0, player 2 at index 1
    public static String[] getPlayers(Scanner key) {
        String[] players = new String[2];
        System.out.print("Player 1 enter your name: ");
        players[0] = key.nextLine();
        System.out.print("Player 2 enter your name: ");
        players[1] = key.nextLine();
        System.out.println();
        return players;
    }

    // Given the array of the current board, display the board with column numbers.
    public static void displayBoard(int[][] board, boolean win) {
        if (win) System.out.println("Final Board");
        else System.out.println("Current Board");
        for (int c = 0; c < COLUMNS; c++)
            System.out.print(c+1 + " ");
        System.out.print(" column numbers");
        System.out.println();
        for (int[] r : board) {
            for (int c : r)
                System.out.print(pieceType(c) + " ");
            System.out.println();
        }
        System.out.println();
    }

    // Ask the user for a column and error check input.
    public static int getColumn(Scanner key, String player) {
        String prompt = (player + ", enter the column to drop your checker: ");
        System.out.print(prompt);
        int column = getInt(key, prompt);
        while (column < 1 || column > 7) {
            System.out.println(column + " is not a valid column.");
            System.out.print(prompt);
            column = getInt(key, prompt);
        }
        return column - 1;
    }

    // Alters the 2d array to reflect having dropped a checker. Requires playerPieceNum to determine
    // which type of checker to drop.
    public static void dropChecker(int[][] board, int playerPieceNum, int turn, Scanner key, String[] players) {
        int column = getColumn(key, players[turn % 2]);
        boolean full = true;
        while (full) {
            int total = 0;
            for (int row[] : board)
                total += Math.abs(row[column]);
            if (total < ROWS) {
                board[ROWS - 1 - total][column] = playerPieceNum;
                full = false;
            } else {
                System.out.println(column + 1 + " is not a legal column. That column is full");
                column = getColumn(key, players[turn % 2]);
            }
        }
    }

    // Check for wins, being passed the 2d board array
    public static boolean checkWin(int[][] board, String player) {
        if (checkHoriz(board) || checkVert(board) || checkSE(board) || checkNE(board)) {
            System.out.println(player + " wins!!");
            return true;
        } else return checkDraw(board);
    }

    public static boolean checkDraw(int[][] board) {
        for (int[] row : board) {
            for (int col : row)
                if (col == EMPTY) return false;
        }
        System.out.println("The game is a draw.");
        return true;
    }

    // Check for horizontal wins using the 2d board array
    public static boolean checkHoriz(int[][] board) {
        for (int[] row : board) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                int sum = 0;
                for (int sumCol = col; sumCol < col + 4; sumCol++) {
                    sum += row[sumCol];
                    if (sum == 4 || sum == -4)
                        return true;
                }
            }
        }
        return false;
    }

    // Check for vertical wins
    public static boolean checkVert(int[][] board) {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                int sum = 0;
                for (int sumRow = row; sumRow < row + 4; sumRow++) {
                    sum += board[sumRow][col];
                    if (sum == 4 || sum == -4)
                        return true;
                }
            }
        }
        return false;
    }

    // Check for southeast wins
    public static boolean checkSE(int[][] board) {
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                int sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += board[row + i][col + i];
                    if (sum == 4 || sum == -4)
                        return true;
                }
            }
        }
        return false;
    }

    // Check for northeast wins
    public static boolean checkNE(int[][] board) {
        for (int row = ROWS - 1; row > 2; row--) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                int sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += board[row - i][col + i];
                    if (sum == 4 || sum == -4)
                        return true;
                }
            }
        }
        return false;
    }


    // Return the string associated with the pieceTypeNumber
    public static String pieceType(int num) {
        if (num == BLACK)
            return "b";
        else if (num == RED)
            return "r";
        else return ".";
    }

    // show the intro
    public static void intro() {
        System.out.println("This program allows two people to play the");
        System.out.println("game of Connect four. Each player takes turns");
        System.out.println("dropping a checker in one of the open columns");
        System.out.println("on the board. The columns are numbered 1 to 7.");
        System.out.println("The first player to get four checkers in a row");
        System.out.println("horizontally, vertically, or diagonally wins");
        System.out.println("the game. If no player gets fours in a row and");
        System.out.println("and all spots are taken the game is a draw.");
        System.out.println("Player one's checkers will appear as r's and");
        System.out.println("player two's checkers will appear as b's.");
        System.out.println("Open spaces on the board will appear as .'s.\n");
    }


    // prompt the user for an int. The String prompt will
    // be printed out. I expect key is connected to System.in.
    public static int getInt(Scanner key, String prompt) {
        while(!key.hasNextInt()) {
            String notAnInt = key.nextLine();
            System.out.println(notAnInt + " is not an integer.");
            System.out.print(prompt);
        }
        int result = key.nextInt();
        key.nextLine();
        return result;
    }
}