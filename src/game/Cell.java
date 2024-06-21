package src.game;

public enum Cell {
    FILLED,
    EMPTY;

    @Override
    public String toString() {
        return this == FILLED ? "▓▓" : "  ";
    }
}
