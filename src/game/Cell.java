package src.game;

public enum Cell {
    FILLED((byte) 1),
    EMPTY((byte) 0),
    MARKED((byte) 0),
    CURSOR((byte) 0);

    private final byte val;

    Cell(byte x) {
        this.val = x;
    }

    @Override
    public String toString() {
        return switch (this) {
            case FILLED -> "▓▓";
            case MARKED -> "░░";
            case EMPTY -> "&nbsp;&nbsp;";
            case CURSOR -> "██";
        };
    }

    public Cell fromVal(byte x) {
        return switch (x) {
            case 1 -> FILLED;
            case 0 -> EMPTY;
            default -> throw new RuntimeException("Parsed invalid byte");
        };

    }

    public byte toVal() {
        return this.val;
    }

}
