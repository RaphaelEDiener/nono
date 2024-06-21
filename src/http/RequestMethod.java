package src.http;

import java.util.*;

public enum RequestMethod {
    GET,
    POST;

    public static Optional<RequestMethod> from_string(String str){
        return switch (str) {
            case "GET" -> Optional.of(GET);
            case "POST" -> Optional.of(POST);
            default -> Optional.empty();
        };
    }
}
