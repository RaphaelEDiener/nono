package src.game;

import static java.lang.Math.clamp;
import java.util.*;
import src.html.*;

public class Game implements HTML {

    public final int width;
    public final int height;
    private final Cell[] data;
    private final Cursor cursor;
    private static final HTMX up_htmx =
            new HTMX().addTrigger(HTMXTrigger.KeyUp).post("/up").target_id("board");
    private static final HTMX left_htmx =
            new HTMX().addTrigger(HTMXTrigger.KeyLeft).post("/left").target_id("board");
    private static final HTMX right_htmx =
            new HTMX().addTrigger(HTMXTrigger.KeyRight).post("/right").target_id("board");
    private static final HTMX down_htmx =
            new HTMX().addTrigger(HTMXTrigger.KeyDown).post("/down").target_id("board");

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Cell[width * height];
        Arrays.fill(data, Cell.FILLED);
        this.cursor = new Cursor(0, 0);
    }
    public Game(Game old, Cursor cursor){
        this.width = old.width;
        this.height = old.height;
        this.data = old.data;
        this.cursor = cursor;
    }

    public Game up() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x(), 0, this.width),
                clamp(this.cursor.y() - 1, 0, this.height)
        ));
    }
    public Game left() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x() - 1, 0, this.width),
                clamp(this.cursor.y(), 0, this.height)
        ));
    }
    public Game right() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x() + 1, 0, this.width),
                clamp(this.cursor.y(), 0, this.height)
        ));
    }
    public Game down() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x(), 0, this.width),
                clamp(this.cursor.y() + 1, 0, this.height)
        ));
    }

    public String innerHtml() {
        StringBuilder ans = new StringBuilder();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.cursor.x() == x && this.cursor.y() == y) {
                    ans.append(this.cursor.toHtml());
                }
                else {
                    ans.append(this.data[y * this.width + x].toString());
                }
            }
            ans.append("<br>");
        }
        return ans.toString();
    }

    public String toHtml() {
        var up_div = new Div("", Game.up_htmx);
        var left_div = new Div("", Game.left_htmx);
        var right_div = new Div("", Game.right_htmx);
        var down_div = new Div("", Game.down_htmx);
        var ans = new Paragraph(this.innerHtml()).id("board");
        return ans.toHtml() + up_div.toHtml() + left_div.toHtml() + right_div.toHtml()
                + down_div.toHtml();
    }
}
