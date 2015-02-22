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

// Ring buffer class to implement the ring buffer used to synthesize guitar string audio
public class RingBuffer {

    private int capacity;
    private double[] ringBuffer;
    private int first;
    private int last;
    private int size;

    // Constructor call. Requires an integer specifying the capacity of the ring buffer.
    public RingBuffer(int c) {
        capacity = c;
        ringBuffer = new double[capacity];
        size = 0;
        first = 0;
        last = 0;
    }

    // Public size method. Returns an int with the number of values that have been put into the array minus
    // any that have been taken out.
    public int size() {
        return size;
    }

    // Public isEmpty method. Returns true if no elements have been added to the buffer of if all items have been
    // removed from teh buffer.
    public boolean isEmpty() {
        return (size == 0);
    }

    // Public isFull method. Returns true if the number of elements added to the buffer is equal to its capacity.
    public boolean isFull() {
        return (size == capacity);
    }

    // Method to add a value to the buffer. Requires the buffer not be full. Adds the value to the end of any existing
    // values.
    public void enqueue(double x) {
        if (size == capacity) throw new IllegalStateException("Cannot enqueue to a full buffer");
        else {
            ringBuffer[last] = x;
            last++;
            size++;
            if (last == capacity) last = 0;
        }
    }

    // Method to remove a value from the buffer. Requires the buffer not be empty. Deletes the value from the beginning
    // of the buffer. Returns the deleted value.
    public double dequeue() {
        double firstElement = ringBuffer[first];
        if (size == 0) throw new IllegalStateException("Cannot dequeue from an empty buffer");
        else {
            first++;
            size--;
            if (first == capacity) first = 0;
            if (size == 0) {
                last = 0;
                first = 0;
            }
        }
        return firstElement;
    }

    // Method that returns the first value of the buffer without deleting it.
    public double peek() {
        if (size == 0) throw new IllegalStateException("The buffer appears to be empty! Please enqueue before peeking.");
        return ringBuffer[first];
    }

    // Returns a string with all of the elements of the buffer in brackets separated by commas.
    // Returns "[]" if the buffer is empty.
    public String toString() {
        String result = "";
        if (size == 0) return "[]";
        else {
            result += "[";
            int index = first;
            for (int i = 0; i < size; i++) {
                if (i != 0) result += " ";
                result += (ringBuffer[index]);
                if (i != size - 1) result += ",";
                index++;
                if (index == capacity) index = 0;
            }
            result += ("]");
        }
        return result;
    }

}
