package src.html;

public enum WidthUnits {
    EM,
    REM,
    PERCENT,
    PX;

    public String toString() {
        return switch (this) {
            case EM -> "em";
            case REM -> "rem";
            case PERCENT -> "%";
            case PX -> "px";
        };
    }
}
