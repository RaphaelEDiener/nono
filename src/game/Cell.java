package src.game;

public enum Cell {
    FILLED(0),
    EMPTY(1),
    MARKED(2),
    CURSOR(3);

    private final int val;

    Cell(int x){
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

    void fromVal();

    void toVal();

}
