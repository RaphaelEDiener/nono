package src.html;

public enum ContentWidthUnits {
    DEVICE_WIDTH;

    public String toString() {
        return switch (this) {
            case DEVICE_WIDTH -> "device-width";
        };
    }
}
