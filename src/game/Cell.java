package src.game;

public enum Cell {
    FILLED,
    EMPTY,
    MARKED,
    CURSOR;

    @Override
    public String toString() {
        return switch (this) {
            case FILLED -> "▓▓";
            case MARKED -> "░░";
            case EMPTY -> "&nbsp;&nbsp;";
            case CURSOR -> "██";
        };
    }
}
