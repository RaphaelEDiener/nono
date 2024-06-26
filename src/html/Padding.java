package src.html;

public final class Padding
implements Style {
    public final int top   ;
    public final int left  ;
    public final int right ;
    public final int bottom;
    public final WidthUnits unit;

    public Padding() {
        this.top    = 0;
        this.left   = 0;
        this.right  = 0;
        this.bottom = 0;
        this.unit = WidthUnits.PX;
    }
    public Padding(
        final int top,
        final int left,
        final int right,
        final int bottom,
        final WidthUnits unit
    ) {
        this.top    = top;
        this.left   = left;
        this.right  = right;
        this.bottom = bottom;
        this.unit = unit;
    }

    public Padding top(final int top) {
        return new Padding(top, this.left, this.right, this.bottom, this.unit);
    }
    public Padding left(final int left) {
        return new Padding(this.top, left, this.right, this.bottom, this.unit);
    }
    public Padding right(final int right) {
        return new Padding(this.top, this.left, right, this.bottom, this.unit);
    }
    public Padding bottom(final int bottom) {
        return new Padding(this.top, this.left, this.right, bottom, this.unit);
    }
    public String toStyle() {
        return "padding: " + this.top + this.unit + " "
        + this.left + this.unit + " "
        + this.right + this.unit + " "
        + this.bottom + this.unit + "; ";
    }
}    
