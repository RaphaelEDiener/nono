package src.game;

import static java.lang.Math.*;
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
    private static final HTMX mark_htmx =
            new HTMX().addTrigger(HTMXTrigger.KeySpace).post("/mark").target_id("board");
    private static final HTMX confirm_htmx =
            new HTMX().addTrigger(HTMXTrigger.KeyEnter).post("/confirm").target_id("board");

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Cell[width * height];
        Arrays.fill(data, Cell.EMPTY);
        this.cursor = new Cursor(0, 0);
    }

    public Game(Game old, Cursor cursor) {
        this.width = old.width;
        this.height = old.height;
        this.data = old.data;
        this.cursor = cursor;
    }

    public Game(Game old, Cell cell, int x, int y) {
        this.width = old.width;
        this.height = old.height;
        this.cursor = old.cursor;
        var new_data = Arrays.copyOf(old.data, this.width * this.height);
        new_data[x + y * this.width] = cell;
        this.data = new_data;
    }

    public Game up() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x(), 0, this.width - 1),
                clamp(this.cursor.y() - 1, 0, this.height - 1)
        ));
    }

    public Game left() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x() - 1, 0, this.width - 1),
                clamp(this.cursor.y(), 0, this.height - 1)
        ));
    }

    public Game right() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x() + 1, 0, this.width - 1),
                clamp(this.cursor.y(), 0, this.height - 1)
        ));
    }

    public Game down() {
        return new Game(this, new Cursor(
                clamp(this.cursor.x(), 0, this.width - 1),
                clamp(this.cursor.y() + 1, 0, this.height - 1)
        ));
    }

    public Game mark() {
        var old_cell = this.data[this.cursor.x() + this.cursor.y() * this.width];
        var new_cell = old_cell == Cell.MARKED ? Cell.EMPTY : Cell.MARKED;
        return new Game(this, new_cell, this.cursor.x(), this.cursor.y());
    }

    public Game confirm() {
        var old_cell = this.data[this.cursor.x() + this.cursor.y() * this.width];
        var new_cell = old_cell == Cell.FILLED ? Cell.EMPTY : Cell.FILLED;
        return new Game(this, new_cell, this.cursor.x(), this.cursor.y());
    }

    public String innerHtml() {
        var arr = Arrays.copyOf(this.data, this.width * this.height);
        arr[cursor.x() + cursor.y() * this.width] = Cell.CURSOR;
        StringBuilder ans = new StringBuilder();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                ans.append(arr[y * this.width + x].toString());
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
        var mark_div = new Div("", Game.mark_htmx);
        var confirm_div = new Div("", Game.confirm_htmx);
        var ans = new Paragraph(this.innerHtml()).id("board").border(new Border(1));
        return ans.toHtml() + confirm_div.toHtml() + mark_div.toHtml() + up_div.toHtml()
                + left_div.toHtml() + right_div.toHtml()
                + down_div.toHtml();
    }
}
