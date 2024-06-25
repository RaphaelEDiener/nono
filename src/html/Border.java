package src.html;

public class Border 
implements Style {
    public final int width; 
    public final WidthUnits unit;

    public Border() {
        this.width = 1;
        this.unit(WidthUnits.PX);
    }
    public Border(int width, WidthUnits unit) {
        this.width = width;
        this.unit = unit;
    }

    public Border width(int width) {
        return new Border(width, this.unit);
    }
    public Border unit(WidthUnits unit) {
        return new Border(this.width, unit);
    }

    public String toStyle() {
        return this.width == 0 ? "" : 
        "border: " + this.width + this.unit + "solid; ";
    }
}
