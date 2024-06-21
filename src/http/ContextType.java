package src.http;

public enum ContextType {
    TEXT_HTML("text/html");

    private final String str;
    ContextType(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}
