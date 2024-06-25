package src.html;

public class Margin
implements Style {
    public final int top   ;
    public final int left  ;
    public final int right ;
    public final int bottom;
    public final WidthUnits unit;

    public Margin() {
        this.top    = 0;
        this.left   = 0;
        this.right  = 0;
        this.bottom = 0;
        this.unit = WidthUnits.PX;
    }
    public Margin(
        int top, 
        int left, 
        int right, 
        int bottom, 
        WidthUnits unit
    ) {
        this.top    = top;
        this.left   = left;
        this.right  = right;
        this.bottom = bottom;
        this.unit = unit;
    }

    public Margin top(int top) {
        return new Margin(top, this.left, this.right, this.bottom, this.unit);
    }
    public Margin left(int left) {
        return new Margin(this.top, left, this.right, this.bottom, this.unit);
    }
    public Margin right(int right) {
        return new Margin(this.top, this.left, right, this.bottom, this.unit);
    }
    public Margin bottom(int bottom) {
        return new Margin(this.top, this.left, this.right, bottom, this.unit);
    }
    public String toStyle() {
        return "margin: " + this.top + this.unit + " "
        + this.left + this.unit + " "
        + this.right + this.unit + " "
        + this.bottom + this.unit + "; "
    }
}    
