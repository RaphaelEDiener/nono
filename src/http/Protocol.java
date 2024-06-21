package src.http;

public enum Protocol {
    HTTP1_1("HTTP/1.1"),
    HTTP2("HTTP/2");
    private final String str;

    Protocol(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}
