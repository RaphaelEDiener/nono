package src.game;

import java.util.*;
import src.html.*;

public class Game implements HTML {

    public final int width;
    public final int height;
    private final Cell[] data;
    private final Cursor cursor;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Cell[width*height];
        Arrays.fill(data, Cell.FILLED);
        this.cursor = new Cursor(0,0);
    }

    private static void up() {
    }

    public String toHtml() {
        StringBuilder ans = new StringBuilder();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.cursor.x() == x && this.cursor.y() == y) {
                    ans.append(this.cursor.toHtml());
                } else {
                    ans.append(this.data[y * this.width + x].toString());
                }
            }
            ans.append("<br>");
        }
        return new Paragraph(ans.toString()).toHtml();
    }
}
