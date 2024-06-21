package src.http;

public record Request(
        RequestMethod method,
        String url,
        Protocol protocol,
        byte[] content
) {

    public static Request from_raw(String in) {
        var method = RequestMethod.GET;
        var url = "/";
        var protocol = Protocol.HTTP1_1;
        var content = new byte[0];
        return new Request(method, url, protocol, content);
    }
}
