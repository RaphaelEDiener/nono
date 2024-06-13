package src;

import src.*;
import java.util.Optional;
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
    static void test(final Frame frame) {
        for (int y = 0; y < frame.height; y++) {
            for (int x = 0; x < frame.width; x++) { 
                System.out.print(
                    "\u001B[" + (y+1) + ";" + (x+1) + "H" + "\u2588"
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
    static void flush(final Frame frame, final Cursor cursor) {
        var chars = frame.chars.clone();
        chars[cursor.x + frame.width * cursor.y] = cursor.symbol;
        System.out.print("\u001B[H" + String.join("", chars) + "\u001B[H");
    }

    static Optional<Pair<Integer, Integer>> dimensions() {
        String os_name = System.getProperty("os.name").toLowerCase();
        return switch (os_name) {
            case String name when name.contains("windows") -> windows_dimensions();
            case String name when name.contains("linux") -> linux_dimensions();
            default -> Optional.empty();
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

    private static Optional<Pair<Integer, Integer>> linux_dimensions() {
        System.out.println("Linux not supported yet"); 
        return Optional.empty();
    }
}
