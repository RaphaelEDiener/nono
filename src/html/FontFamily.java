package src.html;

public enum FontFamily implements Style {
    MONOSPACE("monospace, monospace"),
    SERIF("serif"),
    SANS_SERIF("sans-serif"),
    CURSIVE("cursive"),
    SYSTEM_UI("system-ui");

    private final String val;

    FontFamily(String val) {
        this.val = val;
    }

    @Override
    public String toStyle() {
        return "font-family: " + this.val + "; ";
    }
}
