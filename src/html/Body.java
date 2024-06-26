package src.html;

public class Body implements HTML {

    public final String content;
    public final Head head;

    public Body(final String content) {
        this.content = content;
    }

    public String toHtml() {
        return "<!doctype html><html lang=\"en-US\">"
        + this.head.toHtml()
        + "<script src=\"https://unpkg.com/htmx.org@2.0.0\"></script>"
        + "<body>"
        + this.content.toHtml()
        + "</body>"
        + "</html>";
    }

    public String innerHtml() {
        return this.content;
    }
}
