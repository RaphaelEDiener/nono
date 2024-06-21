package src.http;

import java.nio.charset.*;

public class Response {

    public final Protocol protocol;
    public final StatusCode code;
    public final ContextType type;
    public final int length;
    public final String content;
    public final Charset charset;

    public Response(
            Protocol protocol,
            StatusCode code,
            ContextType type,
            String content,
            Charset charset
    )
    {
        this.protocol = protocol;
        this.content = content;
        this.code = code;
        this.type = type;
        this.charset = charset;
        this.length = content.getBytes(charset).length;
    }

    @Override
    public String toString() {
        return this.protocol + " " + this.code + "\n" +
                "Content-Type: " + this.type + "\n" +
                "Content-Length: " + this.length + "\n\n" +
                this.content + "\n";
    }

    public byte[] getBytes(){
        return this.toString().getBytes(this.charset);
    }
}
