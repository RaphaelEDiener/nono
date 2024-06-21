package src.http;

import java.util.*;

public enum Protocol {
    HTTP1_1("HTTP/1.1"),
    HTTP2("HTTP/2");
    private final String str;

    Protocol(String str) {
        this.str = str;
    }

    public static Optional<Protocol> from_string(String str) {
        return switch (str) {
            case "HTTP/1.1" -> Optional.of(HTTP1_1);
            case "HTTP/2" -> Optional.of(HTTP2);
            default -> Optional.empty();
        };
    }

    @Override
    public String toString() {
        return this.str;
    }
}
