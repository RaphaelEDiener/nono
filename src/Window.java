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

    private Frame cur_frame;
    private Frame new_frame;
//    private OutStreamWriter out;

    public Window(
        Frame cur_frame,
        Frame new_frame
    ) {
        this.cur_frame = cur_frame;
        this.new_frame = new_frame;
  //      this.out = new OutStreamWriter(System.out, StandardCharsets.UTF_8)
    }

    /**
     * manually updates every character
     */
    void draw() {
        for (int y = 0; y < cur_frame.height; y++) {
            for (int x = 0; x < cur_frame.width; x++) { 
                System.out.print("\u001B[" + (y+1) + ";" + (x+1) + "H" + cur_frame.chars[y*cur_frame.width + x]);
            }
        }
    }

    /**
     * Issues a single print
     */
    void clear() {
        System.out.print("\u001B[H" + new String(cur_frame.chars));
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

    public static Optional<Pair<Integer, Integer>> dimensions() {
        String os_name = System.getProperty("os.name").toLowerCase();
        return switch (os_name) {
            case String name when name.contains("windows") -> windows_dimensions();
            default -> Optional.empty();
        };
    }

}
