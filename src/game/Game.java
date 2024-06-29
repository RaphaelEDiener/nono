package src.game;

import static java.lang.Math.*;

import java.io.FileOutputStream;
import java.util.*;
import src.html.*;

public class Game {

    public final int width;
    public final int height;
    private final Cell[] data;
    private final Cursor cursor;
    public final String name;
    public final int click;
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
    private static final HTMX play_htmx =
            new HTMX().addTrigger(HTMXTrigger.Click).post("/play").target_id("board");
    private static final HTMX create_htmx =
            new HTMX().addTrigger(HTMXTrigger.Click).post("/create").target_id("board");

    public Game(int width, int height, String name) {
        this.name = name;
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
        //save for create project?
    }

    public Game create() {
    	//ask for name , width and height
    	return new Game(width,height,name);
    }
    
    
    public Game check(data1,data2) {
    	var current = Arrays.copyOf(data1.data, data1.width * data1.height);
    	var solved = Arrays.copyOf(data2.data, data2.width * data2.height);
    	
    	boolean lineCorrect = true;
    	boolean rowCorrect = true;
    	boolean cellCorrect = true;
    	
    	int filledC = 0;
    	int filledS = 0;
    	int cellNumber = 0;
    	int collumsNumb = 0;
    	
    	
    	for(int y = 0; y < data1.width; y++) {
    		for(int x = 0; x < data1.height; x++) {
    			if(data1.cell[cellNumber] == Cell.FILLED ) {
    				filledC ++;
    			}
    			if(data2.cell[cellNumber] == Cell.FILLED) {
    				filledS ++;
    			}
    			if(data1.cell[cellNumber] != data2.cell[cellNumber]) {
    				cellCorrect = false;
    			}
    			cellNumber++;
    		}
    		if(filledC != filledS && cellCorrect == false) {
    			return LineError(y+1);
    		}
    	}
    	for(int y = 0; y < data1.width; y++) {
//    		for(int x = 0; x < data1.height, x++)
    		if(data1.cell[data1])
    		
    	}

    }

    public Game play() {
        //click building if confirm them click ++ ?
        //1 Time Stemp

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
}
