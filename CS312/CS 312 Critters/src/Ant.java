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

public class Ant extends Critter {

    private Direction walkDirection;
    private int moveCounter;

    // Constructor, requires a boolean for which direction the ant goes.
    public Ant (boolean walkSouth) {
        if (walkSouth) walkDirection = Direction.SOUTH;
        else walkDirection = Direction.NORTH;
        moveCounter = 0;
    }

    // Ants always eat. Fscking gluttons.
    public boolean eat() {
        return true;
    }

    // Attack method. Always scratch.
    public Attack fight(String opponent) {
        return Attack.SCRATCH;
    }

    // Move method. Ants always go south. Watch out South America!
    public Direction getMove() {
        moveCounter++;
        if (moveCounter % 2 == 1) return walkDirection;
        else return Direction.EAST;
    }

    // Ants are red
    public Color getColor() {
        return Color.RED;
    }

    // Ants look like percent signs
    public String toString() {return "%";}
}