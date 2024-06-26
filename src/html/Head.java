package src.html;

public final class Head implements HTML {

    public final String title;
    public final ContentWidthUnits width;
    public final Favicon favicon;

    public Head(
        final String title,
        final ContentWidthUnits width,
        final Favicon favicon
    ) {
        this.title   = title;
        this.width   = width;
        this.favicon = favicon;
    }

    public String toHtml() {
        return "<head> "
        + this.innerHtml()
        + "</head>";
    }

    public String innerHtml(){
        return "<meta charset=\"UTF-8\" />"
        + "<meta name=\"viewport\" content=\"width=" + width.toString() + "\" />"
        + favicon.toHtml()
        + "<title>" + title + "</title>";
    }
}
