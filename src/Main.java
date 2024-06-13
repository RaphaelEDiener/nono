package src;

import java.util.Optional;
import src.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        // Get windows size
        // unix systems: 
        // "\x1b[H"             // home
        // "\x1b[5000;5000H"    // move to col 5000 row 5000
        // "\x1b[6n"            // request cursor position
        // "\x1b[H"             // home
        // Frame b = new Frame(-1, 0, new char[]{});
        // Frame c = new Frame(0, -1, new char[]{});
        // Frame d = new Frame(1, 1, new char[]{});
        var x = Window.dimensions();
        if (x.isEmpty()) {return;}
        var frame = Frame.empty(x.get());
        // TODO: promote cursor to class (i guess)
        var cursor = new Cursor(0, 0, "\u2588", frame);
        Window.test(frame);
        while (true) {
            try {
                var key_op = Keys.from_console_in(System.in.read());
                if (key_op.isEmpty()) {continue;}
                else {
                    var new_ = Window.update(frame, cursor, key_op.get());
                    frame = new_.first;
                    cursor = new_.second;
                    Window.flush(frame, cursor);
                }
            } catch (IOException ignore) {
                // Exit program
            }
        }
    }
}
