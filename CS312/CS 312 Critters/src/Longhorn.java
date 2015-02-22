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

// Longhorn class. Very powerful.
public class Longhorn extends Critter {

    private int numberMoves;
    private static Color BURNT_ORANGE = new Color(207,83,0);
    private Direction direction;
    private int counterNS;
    private int longhornType;
    private String antString;
    private String fullHippoString;
    private int potentialEats;

    // Constructor, no parameters, sets instance variables.
    public Longhorn() {
        numberMoves = 0;
        direction = Direction.NORTH;
        counterNS = 0;
        longhornType = (int)(Math.round(Math.random()));
        potentialEats = 0;
        antString = new Ant(true).toString();
        fullHippoString = new Hippo(0).toString();
    }

    // Longhorns eat if there's nothing in their immediate vicinity and if they haven't eat then last 5 times they might have eaten.
    // This allows them to perpetually increase their score, even when all of the other critters are dead.
    public boolean eat() {
        potentialEats++;
        String neighbors = "";
        neighbors += (getNeighbor(Direction.NORTH) + getNeighbor(Direction.SOUTH) + getNeighbor(Direction.EAST) + getNeighbor(Direction.WEST));
        if (getNeighbor(Direction.NORTH).equals(" ") && getNeighbor(Direction.EAST).equals(" ") && getNeighbor(Direction.WEST).equals(" ")
                && !neighbors.contains(antString) && !neighbors.contains("V") && !neighbors.contains("^")
                && !neighbors.contains(">") && !neighbors.contains("<") && !neighbors.contains(fullHippoString)  && (potentialEats % 5 == 0))
            return true;
        else return false;
    }

    // Longhorns fight appropriate to their opponent and win every time.
    public Attack fight(String opponent) {
        if (opponent.equals(antString))
            return Attack.ROAR;
        else if (opponent.equals("V") || opponent.equals("^") || opponent.equals("<") || opponent.equals(">"))
            return Attack.SCRATCH;
        else if (opponent.equals(fullHippoString))
            return Attack.SCRATCH;
        else if (opponent.charAt(0) > 48 && opponent.charAt(0) < 58)
            return Attack.ROAR;
        else return Attack.POUNCE;
    }

    // Longhorns will either move N or S the height of the map and then juke E or W OR if a critter is neighboring them, they will pursue it.
    public Direction getMove() {
        numberMoves++;
        if (getNeighbor(Direction.NORTH).contains(antString) || getNeighbor(Direction.NORTH).contains(fullHippoString) || getNeighbor(Direction.NORTH).contains("V")
            || getNeighbor(Direction.NORTH).contains("^") || getNeighbor(Direction.NORTH).contains("<") || getNeighbor(Direction.NORTH).contains(">") || getNeighbor(Direction.NORTH).contains("S")
            || (getNeighbor(Direction.NORTH).charAt(0) > 48 && getNeighbor(Direction.NORTH).charAt(0) < 58))
            return Direction.NORTH;
        else if (getNeighbor(Direction.SOUTH).contains(antString) || getNeighbor(Direction.SOUTH).contains(fullHippoString) || getNeighbor(Direction.SOUTH).contains("V")
                || getNeighbor(Direction.SOUTH).contains("^") || getNeighbor(Direction.SOUTH).contains("<") || getNeighbor(Direction.SOUTH).contains(">") || getNeighbor(Direction.SOUTH).contains("S")
                || (getNeighbor(Direction.SOUTH).charAt(0) > 48 && getNeighbor(Direction.SOUTH).charAt(0) < 58))
            return Direction.SOUTH;
        else if (getNeighbor(Direction.EAST).contains(antString) || getNeighbor(Direction.EAST).contains(fullHippoString) || getNeighbor(Direction.EAST).contains("V")
                || getNeighbor(Direction.EAST).contains("^") || getNeighbor(Direction.EAST).contains("<") || getNeighbor(Direction.EAST).contains(">") || getNeighbor(Direction.EAST).contains("S")
                || (getNeighbor(Direction.EAST).charAt(0) > 48 && getNeighbor(Direction.EAST).charAt(0) < 58))
            return Direction.EAST;
        else if (getNeighbor(Direction.WEST).contains(antString) || getNeighbor(Direction.WEST).contains(fullHippoString) || getNeighbor(Direction.WEST).contains("V")
                || getNeighbor(Direction.WEST).contains("^") || getNeighbor(Direction.WEST).contains("<") || getNeighbor(Direction.WEST).contains(">") || getNeighbor(Direction.WEST).contains("S")
                || (getNeighbor(Direction.WEST).charAt(0) > 48 && getNeighbor(Direction.WEST).charAt(0) < 58))
            return Direction.WEST;
        else if (longhornType == 0) {
            if (numberMoves < getHeight() + 10) return Direction.SOUTH;
            else if (numberMoves == getHeight() + 10) return Direction.WEST;
            else {
                counterNS++;
                if (counterNS % 2 == 0) direction = Direction.NORTH;
                else direction = Direction.SOUTH;
                numberMoves = 0;
                return direction;
            }
        }
        else {
            if (numberMoves < getHeight() + 10) return direction;
            else if (numberMoves == getHeight() + 10) return Direction.EAST;
            else {
                counterNS++;
                if (counterNS % 2 == 0) direction = Direction.NORTH;
                else direction = Direction.SOUTH;
                numberMoves = 0;
                return direction;
            }
        }
    }

    // Longhorns are burnt orange
    public Color getColor() {
        return BURNT_ORANGE;
    }

    // Longhorns look cool.
    public String toString() {
        return "\u024E";
    }
}