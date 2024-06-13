package src;

import src.*;

class Cursor {
    final int x;
    final int y;
    final int max_x;
    final int max_y;
    final String symbol;

    Cursor(
        final int x, 
        final int y, 
        final String symbol, 
        final Frame frame
    ) {
        this.x = x;
        this.y = y;
        this.max_x = frame.width;
        this.max_y = frame.height;
        this.symbol = symbol;
    }
    Cursor(
        final int x, 
        final int y, 
        final String symbol, 
        final int max_x,
        final int max_y
    ) {
        this.x = x;
        this.y = y;
        this.max_x = max_x;
        this.max_y = max_y;
        this.symbol = symbol;
    }

    Cursor up(final int amount) {
        assert amount >= 0 : 
        "moved the cursor up by a negative amount - move it down instead";
        var new_y = this.y - amount;
        new_y = new_y < 0 ? 0 : new_y;
        return new Cursor(this.x, new_y, this.symbol, this.max_x, this.max_y);
    }
    Cursor down(final int amount) {
        assert amount >= 0 : 
        "moved the cursor down by a negative amount - move it up instead";
        var new_y = this.y + amount;
        new_y = new_y > max_y ? max_y : new_y;
        return new Cursor(this.x, new_y, this.symbol, this.max_x, this.max_y);
    }
    Cursor left(final int amount) {
        assert amount >= 0 : 
        "moved the cursor left by a negative amount - move it right instead";
        var new_x = this.x - amount;
        new_x = new_x < 0 ? 0 : new_x;
        return new Cursor(new_x, this.y, this.symbol, this.max_x, this.max_y);
    }
    Cursor right(final int amount) {
        assert amount >= 0 : 
        "moved the cursor right by a negative amount - move it left instead";
        var new_x = this.x + amount;
        new_x = new_x > max_x ? max_x : new_x;
        return new Cursor(new_x, this.y, this.symbol, this.max_x, this.max_y);
    }

}
