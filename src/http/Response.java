package src.http;

import java.nio.charset.*;

public class Response {

    public final Protocol protocol;
    public final StatusCode code;
    public final ContextType type;
    public final int length;
    public final String content;
    public final Charset charset;
    public final String location;

    public static Response ok(
            Protocol protocol,
            StatusCode code,
            ContextType type,
            String content,
            Charset charset
    )
    {
        assert code != StatusCode.SEE_OTHER : "construct a redirect request for 300 status codes";
        return new Response(
                protocol,
                code,
                type,
                content,
                charset,
                ""
        );
    }

    public static Response redirect(
            Protocol protocol,
            StatusCode code,
            Charset charset,
            String location
    )
    {
        assert code != StatusCode.OK : "construct a ok request for 200 status codes";
        assert code
                != StatusCode.INTERNAL_SERVER_ERROR : "construct a ok request for 500 status codes";
        assert code != StatusCode.BAD_REQUEST
                && code != StatusCode.NOT_FOUND : "construct a ok request for 400 status codes";
        return new Response(
                protocol,
                code,
                ContextType.TEXT_PLAIN,
                "",
                charset,
                location
        );
    }

    private Response(
            Protocol protocol,
            StatusCode code,
            ContextType type,
            String content,
            Charset charset,
            String location
    )
    {
        this.protocol = protocol;
        this.content = content;
        this.code = code;
        this.type = type;
        this.charset = charset;
        this.length = content.getBytes(charset).length;
        this.location = location;
    }

    @Override
    public String toString() {
        return switch (this.code) {
            case SEE_OTHER -> this.protocol + " " + this.code + "\n" +
                "Location: " + this.location + "\n";
            case OK, INTERNAL_SERVER_ERROR, BAD_REQUEST, NOT_FOUND ->
                    this.protocol + " " + this.code + "\n" +
                            "Content-Type: " + this.type + "\n" +
                            "Content-Length: " + this.length + "\n\n" +
                            this.content + "\n";

        };
    }

    public byte[] getBytes() {
        return this.toString().getBytes(this.charset);
    }
}
