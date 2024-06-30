package src.http;

import java.util.*;

public enum RequestType {
    NOT_FOUND_PAGE,
    FAVICON,
    HTACCESS,
    SHUT_DOWN;

    public static Optional<RequestType> from_request(Request req) {
        return switch (req.url) {
            case "/favicon.svg" -> Optional.of(FAVICON);
            case ".htaccess" -> Optional.of(HTACCESS);
            case "/not-found-page" -> Optional.of(NOT_FOUND_PAGE);
            default -> Optional.empty();
        };
    }
}
