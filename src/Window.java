package src;

import src.Pair;
import java.util.Optional;
import java.lang.ProcessBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * the window class is our IO class.
 * it handles reading and writing to the screen
 */
class Window {

    /**
     * manually updates every character
     */
    static void draw(final Frame frame) {
        for (int y = 0; y < frame.height; y++) {
            for (int x = 0; x < frame.width; x++) { 
                System.out.print("\u001B[" + (y+1) + ";" + (x+1) + "H" + frame.chars[y*frame.width + x]);
            }
        }
    }

    static void clear() {
        System.out.print("\u001B[2J\u001B[H");
    }

    /**
     * Issues a single print
     */
    static void flush(final Frame frame, final Pair<Integer, Integer> cursor) {
        var chars = frame.chars.clone();
        chars[cursor.first + frame.width * cursor.second] = 254;
        System.out.print("\u001B[H" + new String(chars));
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

    public static Optional<Pair<Integer, Integer>> dimensions() {
        String os_name = System.getProperty("os.name").toLowerCase();
        return switch (os_name) {
            case String name when name.contains("windows") -> windows_dimensions();
            case String name when name.contains("linux") -> linux_dimensions();
            default -> Optional.empty();
        };
    }

    static Pair<Frame, Pair<Integer,Integer>> update(final Frame last_frame, final Pair<Integer,Integer> last_cursor) {
        return new Pair<Frame, Pair<Integer,Integer>>(Frame(last_frame), Pair(last_cursor))
    }
}
