package src;
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
        var cursor = new Pair<Integer, Interger> (0, 0);
        Window.clear();
        while (true) {
            try {
                switch (Keys.from_colole_in(System.in.read())) {
                    case Option<Keys> x when x.isEmpty(): continue;
                    default: {
                        var new = Window.update(frame, cursor);
                        frame = new.first;
                        cursor = new.second;
                        Window.flush(frame, cursor);
                    };
                }
            } catch (IOException ignore) {
                // Exit program
            }
        }
    }
}
