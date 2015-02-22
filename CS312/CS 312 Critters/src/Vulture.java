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

// Vulture class. Very like bird.
public class Vulture extends Bird {

    private boolean hasEaten;
    private boolean hasFought;
    private String antString;

    // Vulture constructor. Just sets instance variables.
    public Vulture() {
        hasEaten = false;
        hasFought = false;
        antString = new Ant(true).toString();
    }

    // Vultures eat once, then don't eat again unless they fight first.
    public boolean eat() {
        if (!hasEaten) {
            hasEaten = true;
            hasFought = false;
            return true;
        } else return false;
    }

    // Vultures pounce on ants or roar at other opponents
    public Attack fight(String opponent) {
        hasFought = true;
        hasEaten = false;
        if (!opponent.equals(antString)) return Attack.POUNCE;
        else return Attack.ROAR;
    }

    // Vultures are black
    public Color getColor() {
        return Color.BLACK;
    }
}
