package src.html;

public final class Div implements HTML {

    public final String content;
    public final HTMX htmx;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;

    public Div() {
        this.content = "";
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border();
    }
    public Div(final String content) {
        this.content = content;
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border();
    }
    public Div(final Div old) {
        this.content = old.content;
        this.htmx    = old.htmx   ;
        this.margin  = old.margin ;
        this.padding = old.padding;
        this.id      = old.id     ;
        this.border  = old.border ;
    }
    public Div(
        final String content,
        final HTMX htmx,
        final Margin margin,
        final Padding padding,
        final Border border,
        final String id
    ) {
        this.content = content;
        this.htmx    = htmx   ;
        this.margin  = margin ;
        this.padding = padding;
        this.id      = id     ;
        this.border  = border ;
    }

    public Div id(String id) {
        return new Div(
            this.content, this.htmx, this.margin, this.padding, this.border, id
        );
    }
    public Div content(String content) {
        return new Div(
            content, this.htmx, this.margin, this.padding, this.border, this.id
        );
    }
    public Div border(Border border){
        return new Div(
            this.content, this.htmx, this.margin, this.padding, border, this.id
        );
    }
    public Div margin(Margin margin) {
        return new Div(
            this.content, this.htmx, margin, this.padding, this.border, this.id
        );
    }
    public Div padding(Padding padding) {
        return new Div(
            this.content, this.htmx, this.margin, padding, this.border, this.id
        );
    }
    public Div htmx(HTMX htmx) {
        return new Div(
            this.content, htmx, this.margin, this.padding, this.border, this.id
        );
    }

    public String toHtml() {
        var id = this.id.isEmpty() ? "" : "id=\"" + this.id + "\" ";
        return "<div style=\"" +
        this.border.toStyle() +
        this.margin.toStyle() +
        this.padding.toStyle() +
        " \" " +
        id +
        this.htmx.toHtml() +
        ">" +
        this.content +
        "</div>";
    }

    public String innerHtml() {
        return this.content;
    }
}
