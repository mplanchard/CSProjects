/**
 * @authors: Student 1: Sam Valdez
 *           Student 2: Matthew Planchard
 * CS312 Assignment 11.
 *
 *  On OUR honor, Sam Valdez and Matthew Planchard, this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *
 * A program to simulate critter interactions
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
import java.awt.*;


// Bird class
public class Bird extends Critter {

    private int moveNumber;
    private Direction lastMove;
    private String antString;

    // Bird constructor. Sets instance variables.
    public Bird() {
        moveNumber = 0;
        lastMove = Direction.NORTH;
        antString = new Ant(true).toString();
    }

    // Birds roar if opponents are ants or DISGUISED AS ANTS. Otherwise, POUNCE.
    public Attack fight(String opponent) {
        if (!opponent.equals(antString)) return Attack.POUNCE;
        else return Attack.ROAR;
    }

    // Birds move in squares. It's like migration.
    public Direction getMove() {
        moveNumber++;
        if (moveNumber == 4) lastMove = Direction.EAST;
        else if (moveNumber == 7) lastMove = Direction.SOUTH;
        else if (moveNumber == 10) lastMove = Direction.WEST;
        else if (moveNumber == 13) {
            moveNumber = 1;
            lastMove = Direction.NORTH;
        }
        return lastMove;
    }

    // Birds are blue?
    public Color getColor() {return Color.BLUE;}

    // Birds look in the direction they're going.
    public String toString() {
        if (lastMove == Direction.NORTH) return "^";
        else if (lastMove == Direction.EAST) return ">";
        else if (lastMove == Direction.SOUTH) return "V";
        else return "<";
    }
}