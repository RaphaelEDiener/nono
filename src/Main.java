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

        Optional<Pair<Integer, Integer>> dim_op = 
            Window.dimensions(OSys.from_args(args));
        if (dim_op.isEmpty()) {return;}
        var frame = Frame.empty(dim_op.get());
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

