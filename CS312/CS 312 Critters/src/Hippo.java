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

// Hippo class
public class Hippo extends Critter {

    private boolean isHungry;
    private int maxHunger;
    private int foodsEaten;
    private int moveNumber;
    private Direction currentDirection;

    // Hippos have a certain hunger, and start out having eaten 0 hunger satiating objects.
    public Hippo (int hunger) {
        maxHunger = hunger;
        isHungry = true;
        foodsEaten= 0;
        currentDirection = Direction.values()[(int) Math.floor(Math.random() * 3.99999)];
    }

    // Hippos eat if they're hungry.
    public boolean eat() {
        if (foodsEaten >= maxHunger) {
            foodsEaten = maxHunger;
            isHungry = false;
            return false;
        } else {
            foodsEaten++;
            return true;
        }
    }

    // HIPPOS CAN POUNCE! But only if they're not hungry. Hungry hippos must scratch.
    public Attack fight(String opponent) {
        if (this.eat()) return Attack.SCRATCH;
        else return Attack.POUNCE;
    }

    public Direction getMove() {
        moveNumber++;
        if (moveNumber == 6) {
            currentDirection = Direction.values()[(int) Math.floor(Math.random() * 3.99999)];
            moveNumber = 1;
        }
        return currentDirection;
    }

    // Hippos are gray if they're hungry; otherwise white
    public Color getColor() {
        if (maxHunger - foodsEaten > 0) return Color.GRAY;
        else return Color.WHITE;
    }

    // Hippos are numbers 1-9 when hungry, 0 when full.
    public String toString() {
        return ("" + (maxHunger - foodsEaten));
    }
}