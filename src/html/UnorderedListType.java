package src.html;

public enum UnorderedListType {
    DISC("disc"),
    CIRCLE("circle"),
    SQUARE("square"),
    DECIMAL("decimal"),
    GEORGIAN("georgian"),
    TRAD_CHINESE_INFORMAL("trad-chinese-informal"),
    KANNADA("kannada");

    private final String val;

    private UnorderedListType(String val) {
        this.val = val;
    }
}
