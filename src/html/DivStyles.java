package src.html;

public final class DivStyles implements StyleClass {

    public final Margin margin;
    public final Padding padding;
    public final Border border;

    public DivStyles(Margin margin, Padding padding, Border border) {
        this.margin = margin;
        this.padding = padding;
        this.border = border;
    }

    public String className() {
        return "";
    }

    public String classDef() {
        return "";
    }

    public String style() {
        return "";
    }
}
