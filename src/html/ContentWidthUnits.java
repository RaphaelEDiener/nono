package src.html;

public enum ContentWidthUnits {
    DEVICE_WIDTH,
    MAX_CONTENT;

    public String toString() {
        return switch (this) {
            case DEVICE_WIDTH -> "device-width";
            case MAX_CONTENT -> "max-content";
        };
    }

    public String toStyle() {
        return "width: " + this + "; ";
    }
}
