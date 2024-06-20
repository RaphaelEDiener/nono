package src;

import java.util.*;
import src.html.*;

public class Game implements HTML {

    public final int width;
    public final int height;
    private final String[] data;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new String[width*height];
        Arrays.fill(data, "██");
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                ans.append(data[y * this.width + x]);
            }
            ans.append("<br>");
        }
        return new Paragraph(ans.toString()).toString();
    }
}
