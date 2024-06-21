package src.http;

import java.util.*;

public record Request(
        RequestMethod method,
        String url,
        Protocol protocol,
        byte[] content
) {

    public static Optional<Request> from_raw(String in) {
        var split = in.split(" ");
        if (split.length != 3) return Optional.empty();

        var method = RequestMethod.from_string(split[0]);
        if (method.isEmpty()) return Optional.empty();
        var url = split[1];
        var protocol = Protocol.from_string(split[2]);
        if (protocol.isEmpty()) return Optional.empty();
        var content = new byte[0];

        return Optional.of(new Request(method.get(), url, protocol.get(), content));
    }
}
