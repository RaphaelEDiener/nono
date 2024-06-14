package src;

import src.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.lang.ProcessBuilder;
import java.io.*;

/**
 * the window class is our IO class.
 * it handles reading and writing to the screen
 */
class Window {

    /**
     * manually updates every character
     */
    static void test(final Frame frame, PrintWriter out) {
        for (int y = 0; y < frame.height; y++) {
            for (int x = 0; x < frame.width; x++) { 
                out.print(
                    "\u001B[" + (y+1) + ";" + (x+1) + "H" + "█" // + "\u2588"
                );
            }
        }
    }

    static void clear() {
        System.out.print("\u001B[2J\u001B[H");
    }

    /**
     * Issues a single print
     */
    static void flush(final Frame frame, final Cursor cursor, final PrintWriter out) {
        var chars = frame.chars.clone();
        chars[cursor.x + frame.width * cursor.y] = cursor.symbol;
        out.print("\u001B[H" + String.join("", chars) + "\u001B[H");
    }

    static Optional<Pair<Integer, Integer>> dimensions(final OSys os) {
        OSys this_os = (os == OSys.UNKNOWN) ? OSys.from_sys_prop() : os;
        return switch (this_os) {
            case WINDOWS -> windows_dimensions();
            case LINUX   -> linux_dimensions();
            case UNKNOWN -> Optional.empty();
        };
    }

    static Pair<Frame, Cursor> update(
        final Frame last_frame, 
        final Cursor last_cursor,
        final Keys key
    ) {
        var new_frame = new Frame(last_frame); 
        var new_cursor = switch (key) {
            case UP    -> last_cursor.up(1);
            case DOWN  -> last_cursor.down(1);
            case LEFT  -> last_cursor.left(1);
            case RIGHT -> last_cursor.right(1);
            default    -> new Cursor(last_cursor);
        };
        return new Pair<Frame, Cursor>(new_frame, new_cursor);
    }

    private static Optional<Pair<Integer, Integer>> windows_dimensions() {
        ProcessBuilder pb = new ProcessBuilder(
            "powershell", 
            "-command",
            "\"&{$H=get-host;$H.ui.rawui;}\"|findstr",
            "/b",
            "WindowSize"
        );
        pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        final int x, y;
        try {
            var process = pb.start();
            process.waitFor();
            String ps_output = process.inputReader().readLine();
            String[] sanitized = ps_output.split(":")[1].split(",");
            x = Integer.parseInt(sanitized[0].trim());
            y = Integer.parseInt(sanitized[1].trim());
        } catch (IOException e) {
            return Optional.empty();
        } catch (InterruptedException e) {                                                                             
            return Optional.empty();
        }
        return Optional.of(new Pair<Integer, Integer>(x, y));
    }

    private static Pair<Integer, Integer> parse_6n(List<Integer> in) {
        var x = 0;
        var y = 0;
        // discard until '['
        while (in.getFirst() != '[') {
            in.removeFirst();
        }
        // ignore '[' 
        in.removeFirst();
        // collect until ';' -> height
        while (in.getFirst() != ';') {
            y = y*10 + in.removeFirst() - '0';
        }
        // ignore ';' 
        in.removeFirst();
        // collect until 'R' -> width
        while (in.getFirst() != 'R') {
            x = x*10 + in.removeFirst() - '0';
        }
        return new Pair<Integer, Integer>(x, y);
    }

    private static Optional<Pair<Integer, Integer>> linux_dimensions() {
        var dims = new Pair<Integer, Integer>(0, 0);
        try {
            System.out.print("\u001B[H\u001B[500;500H\u001B[6n");
            var bytes = new ArrayList<Integer>();
            bytes.add(System.in.read());
            while (bytes.getLast() != 'R') {
                bytes.add(System.in.read());
            }
            dims = parse_6n(bytes);
            System.out.print("\u001B[H");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(dims);
    }
}
