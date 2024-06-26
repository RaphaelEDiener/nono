package src.html;

public final class Body implements HTML {

    public final String content;
    public final Head head;

    public Body(final String content, final Head head) {
        this.content = content;
        this.head = head;
    }

    public Body(final String content) {
        this.content = content;
        this.head = new Head(
                "My Page",
                ContentWidthUnits.DEVICE_WIDTH,
                new Favicon(ImageType.SVG, "/favicon.svg")
        );
    }

    public String toHtml() {
        return "<!doctype html><html lang=\"en-US\">"
                + this.head.toHtml()
                // TODO: download strip and wrap HTMX in java
                + "<script src=\"https://unpkg.com/htmx.org@2.0.0\"></script>"
                + "<body>"
                + this.content
                + "</body>"
                + "</html>";
    }

    public String innerHtml() {
        return this.content;
    }
}
