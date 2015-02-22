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

// Method to provide the user with keys associated with strings of different notes.
public class GuitarHero {

    // Start the Guitar Simulator. Makes an array of GuitarStrings of the required frequencies and a String of keys
    // associated with those frequencies. Displays the window and starts the play method.
    public static void main(String[] args) {
        GuitarString[] notes = new GuitarString[37];
        for (int i = 0; i < 37; i++)
            notes[i] = new GuitarString(440 * Math.pow(1.05956, (i - 24)));
        String keys = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        displayText(0.5, 0.7);
        play(notes, keys);
    }

    // Method to allow the user to 'play the guitar'. Checks to see if the user has pressed a key. If they have,
    // it plucks the associated string, adds together the samples of any currently playing strings, and sends that
    // to standard audio to be played. Plays until the user closes the window.
    private static void play(GuitarString[] notes, String keys) {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keys.indexOf(key) != -1)
                    notes[keys.indexOf(key)].pluck();
            }
            double sample = 0;
            for (GuitarString string : notes)
                    sample += string.sample();
            StdAudio.play(sample);
            for (GuitarString string : notes)
                string.tic();
        }
    }

    // Display the on-screen text.
    private static void displayText(double X_POS, double Y_POS) {
        StdDraw.text(X_POS, Y_POS, "Play a note using the following keys!");
        StdDraw.text(X_POS, Y_POS - 0.1, "q 2 w e 4 r 5 t y 7 u 8 i 9 o p - [ = ");
        StdDraw.text(X_POS, Y_POS - 0.2, "z x d c f v g b n j m k , . ; / ' [space]");
    }

}
