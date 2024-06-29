package src.html;

public enum TextAlign {
    CENTER,
    LEFT,
    RIGHT,
    JUSTIFY;

    public String toString() {
        return switch (this) {
            case CENTER -> "center";
            case LEFT -> "left";
            case RIGHT -> "right";
            case JUSTIFY -> "justify";
        };
    }

    public String toStyle() {
        return "text-align: " + this + "; ";
    }
}
