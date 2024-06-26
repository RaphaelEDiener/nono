package src.html;

public final class Favicon implements Meta {

    public final ImageType type;
    public final String url;

    public Favicon(final ImageType type, final String url) {
        this.type = type;
        this.url = url;
    }

    public String toHtml() {
        return "<link rel=\"icon\" type=\""
        + this.type.toString() 
        + "\" href=\""
        + this.url +
        "\">";
    }
}
