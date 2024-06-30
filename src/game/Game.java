package src.game;

import static java.lang.Math.*;
import java.io.*;
import java.nio.*;
import java.time.*;
import java.util.*;
import src.html.*;

public class Game {

    public final int width;
    public final int height;
    public final Cell[] data;
    public final Cursor cursor;
    public final String name;
    public final int clicks;
    public final LocalDateTime start_time;
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

    public Game(int width, int height, String name) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.data = new Cell[width * height];
        Arrays.fill(data, Cell.EMPTY);
        this.cursor = new Cursor(0, 0);
        this.clicks = 0;
        this.start_time = LocalDateTime.now();
    }

    public Game(Game old, Cursor cursor) {
        this.width = old.width;
        this.height = old.height;
        this.data = old.data;
        this.cursor = cursor;
        this.clicks = old.clicks;
        this.name = old.name;
        this.start_time = old.start_time;
    }

    public Game(
            int width,
            int height,
            Cell[] data,
            Cursor cursor,
            int clicks,
            String name,
            LocalDateTime start_time
    )
    {
        this.width = width;
        this.height = height;
        this.data = data;
        this.cursor = cursor;
        this.clicks = clicks;
        this.name = name;
        this.start_time = start_time;
    }

    public Game(Game old, Cell cell, int x, int y, int clicks) {
        this.width = old.width;
        this.height = old.height;
        this.cursor = old.cursor;
        var new_data = Arrays.copyOf(old.data, this.width * this.height);
        new_data[x + y * this.width] = cell;
        this.data = new_data;
        this.clicks = clicks;
        this.name = old.name;
        this.start_time = old.start_time;
    }

    public static Optional<Game> fromSave(String name) {
        Optional<Game> ans = Optional.empty();
        try {
            var stream = new FileInputStream(name);
            var width = ByteBuffer.wrap(stream.readNBytes(4)).getInt();
            var height = ByteBuffer.wrap(stream.readNBytes(4)).getInt();
            var read = stream.readNBytes(width * height);
            // solved this cursed way because java's stream api is half-baked bs!
            var data = new ArrayList<Cell>();
            for (var b : read) {
                data.add(Cell.fromVal(b));
            }
            var start_time = LocalDateTime.now();

            ans = Optional.of(new Game(
                    width,
                    height,
                    data.toArray(Cell[]::new),
                    new Cursor(0, 0),
                    0,
                    name,
                    start_time
            ));
        }
        catch (Exception ignore) {
        }
        return ans;
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
        return new Game(this, new_cell, this.cursor.x(), this.cursor.y(), this.clicks);
    }

    public Game confirm() {
        var old_cell = this.data[this.cursor.x() + this.cursor.y() * this.width];
        var new_cell = old_cell == Cell.FILLED ? Cell.EMPTY : Cell.FILLED;
        var ans = new Game(
                this,
                new_cell,
                this.cursor.x(),
                this.cursor.y(),
                this.clicks + 1
        );
        ans.save();
        return ans;
    }

    // public Game check(Game solved) {
    //     var current = Arrays.copyOf(this.data, this.width * this.height);
    //     var solved = Arrays.copyOf(data2.data, data2.width * data2.height);

    //     boolean lineCorrect = true;
    //     boolean rowCorrect = true;
    //     boolean cellCorrect = true;

    //     int filledC = 0;
    //     int filledS = 0;
    //     int cellNumber = 0;
    //     int collumsNumb = 0;

    //     for (int y = 0; y < this.height; y++) {
    //         for (int x = 0; x < this.width; x++) {
    //             if (this.cell[cellNumber] == Cell.FILLED) {
    //                 filledC++;
    //             }
    //             if (data2.cell[cellNumber] == Cell.FILLED) {
    //                 filledS++;
    //             }
    //             if (this.cell[cellNumber] != data2.cell[cellNumber]) {
    //                 cellCorrect = false;
    //             }
    //             cellNumber++;
    //         }
    //         if (filledC != filledS && cellCorrect == false) {
    //             return LineError(y + 1);
    //         }
    //     }
    //     for (int y = 0; y < this.width; y++) {
    //   		for(int x = 0; x < this.height, x++)
    //         if (this.cell[data1])

    //     }

    // }

    public void save() {
        try {
            var stream = new FileOutputStream(this.name);
            byte[] width = ByteBuffer.allocate(4).putInt(this.width).array();
            byte[] height = ByteBuffer.allocate(4).putInt(this.height).array();
            stream.write(width);
            stream.write(height);
            for (var cell : this.data) {
                stream.write(cell.toVal());
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
        var up_div = new Div("").htmx(Game.up_htmx);
        var left_div = new Div("").htmx(Game.left_htmx);
        var right_div = new Div("").htmx(Game.right_htmx);
        var down_div = new Div("").htmx(Game.down_htmx);
        var mark_div = new Div("").htmx(Game.mark_htmx);
        var confirm_div = new Div("").htmx(Game.confirm_htmx);
        var paragraph = new Paragraph(this.innerHtml())
                .id("board")
                .border(new Border(1, WidthUnits.EM))
                .fontFamily(FontFamily.MONOSPACE);
        return paragraph.toHtml() + confirm_div.toHtml() + mark_div.toHtml() + up_div.toHtml()
                + left_div.toHtml() + right_div.toHtml()
                + down_div.toHtml();
    }

    /**
     * equality checks the width, height, name and field data for equality. it doesn't consider
     * cursor position or clicks
     *
     * @param obj to compare to
     * @return if they are equal
     */
    public boolean equals(final Object obj) {
        if (!(obj instanceof Game other)) {
            return false;
        }
        else {
            if (this.width != other.width) {
                return false;
            }
            if (this.height != other.height) {
                return false;
            }
            if (!this.name.equals(other.name)) {
                return false;
            }
            for (int i = 0; i < this.data.length; i++) {
                if (this.data[i] != other.data[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
