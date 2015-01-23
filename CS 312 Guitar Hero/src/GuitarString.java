/**
 * CS312 Assignment 12.
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
 * Program that implements an audio synthesis algorithm to play guitar strings.
 */

import java.util.Random;

// Class to implement the synthesis of guitar string audio.
public class GuitarString {

    private static final double decayFactor = 0.994;
    private double frequency;
    private RingBuffer ringBuffer;
    private int capacity;
    private int time;

    // Constructor to create a guitar string object of a given frequency (note). Requires that the frequency be
    // passed as a parameter.
    public GuitarString(double freq) {
        frequency = freq;
        capacity = ((int) (Math.ceil(44100 / frequency)));
        ringBuffer = new RingBuffer(capacity);
        for (int i = 0; i < capacity; i++)
            ringBuffer.enqueue(0.0);
        time = 0;
    }

    // Constructor to create a guitar string object with a given ring buffer for testing.
    public GuitarString(double[] init) {
        capacity = init.length;
        ringBuffer = new RingBuffer(capacity);
        for (double d : init)
            ringBuffer.enqueue(d);
    }

    // Simulates the plucking of a string by assigning random values to the ring buffer.
    public void pluck() {
        Random r = new Random();
        for (int i = 0; i < capacity; i++) {
            ringBuffer.dequeue();
            ringBuffer.enqueue(r.nextDouble() - 0.5);
        }
    }

    // Simulates the passage of time following a pluck by averaging the first two values of the ring buffer, deleting the
    // first one, and adding their average times a decay factor to the end of the ring buffer. Will eventually result in
    // all values going to zero, simulating the actual vibration of a string over time.
    public void tic() {
        double val1 = ringBuffer.dequeue();
        double val2 = ringBuffer.peek();
        double newVal = (val1 + val2) / 2 * decayFactor;
        ringBuffer.enqueue(newVal);
        time++;
    }

    // Returns the first value of the ring buffer.
    public double sample() {
        return ringBuffer.peek();
    }

    // Returns the number of times tic has been called on a given guitar string.
    public int time() {
        return time;
    }
}
