package src.http;

public enum ContextType {
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    IMAGE_SVG("image/svg+xml");

    private final String str;
    ContextType(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}
