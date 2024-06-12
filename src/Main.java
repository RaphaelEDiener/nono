package src;
import src.*;

public class Main {
    public static void main (String[] args) {
        // Get windows size
        // windows: powershell -command "&{$H=get-host;$H.ui.rawui;}"|findstr /b WindowSize
        // unix systems: 
        // "\x1b[H"             // home
        // "\x1b[5000;5000H"    // move to col 5000 row 5000
        // "\x1b[6n"            // request cursor position
        // "\x1b[H"             // home
        Frame a = new Frame(0, 0, new char[]{});
        // Frame b = new Frame(-1, 0, new char[]{});
        // Frame c = new Frame(0, -1, new char[]{});
        // Frame d = new Frame(1, 1, new char[]{});
        var x = Window.dimensions().get();
        var main_window = new Window(Frame.empty(x), Frame.empty(x));
        // TODO: draw function should diff the frames and 
        // composite length output for console
        // (manual / batch cursor movement)
        while (true) {
            main_window.clear();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}
