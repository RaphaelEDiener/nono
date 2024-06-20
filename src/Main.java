package src;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        final Server main_server = new Server(true);
        System.out.println("port: " + main_server.port);
        main_server.start();
    }

    public static void old_main(String[] args)
    {
        // Get windows size
        // unix systems: 
        // "\x1b[H"             // home
        // "\x1b[5000;5000H"    // move to col 5000 row 5000
        // "\x1b[6n"            // request cursor position
        // "\x1b[H"             // home

        Optional<Pair<Integer, Integer>> dim_op =
                Window.dimensions(OSys.from_args(args));
        if (dim_op.isEmpty()) {
            return;
        }
        System.out.println(dim_op.get().first + " " + dim_op.get().second);
        var frame = Frame.full(dim_op.get());
        var cursor = new Cursor(0, 0, "█", frame);
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(System.out, StandardCharsets.UTF_16)
        );
        Window.test(frame, out);
        while (true) {
            try {
                var key_op = Keys.from_console_in(System.in.read());
                if (key_op.isEmpty()) {
                    continue;
                }
                else {
                    var new_ = Window.update(frame, cursor, key_op.get());
                    frame = new_.first;
                    cursor = new_.second;
                    Window.clear();
                    Window.flush(frame, cursor, out);
                }
            }
            catch (IOException ignore) {
                // Exit program
            }
        }
    }
}

