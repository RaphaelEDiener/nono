package src.html;

public enum  {
    EM,
    REM,
    PERCENT,
    PX;

    @Override
    public String toString() {
        return switch (this) {
            case EM -> "em";
            case REM -> "rem";
            case PERCENT -> "%";
            case PX -> "px";
        };
    }
}
