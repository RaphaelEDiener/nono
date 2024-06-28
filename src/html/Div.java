package src.html;

public final class Div implements HTML {

    public final String content;
    public final HTMX htmx;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;
    public final TextAlign text_align;
    public final Color background_color;

    public Div() {
        this.content = "";
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border(0, WidthUnits.PX);
        this.text_align = TextAlign.LEFT;
        this.background_color = Color.TRANSPARENT;
    }

    public Div(final String content) {
        this.content = content;
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border(0, WidthUnits.PX);
        this.text_align = TextAlign.LEFT;
        this.background_color = Color.TRANSPARENT;
    }

    public Div(final Div old) {
        this.content = old.content;
        this.htmx = old.htmx;
        this.margin = old.margin;
        this.padding = old.padding;
        this.id = old.id;
        this.border = old.border;
        this.text_align = old.text_align;
        this.background_color = old.background_color;
    }

    public Div(
            final String content,
            final HTMX htmx,
            final Margin margin,
            final Padding padding,
            final Border border,
            final String id,
            final TextAlign textAlign,
            final Color backgroundColor
    )
    {
        this.content = content;
        this.htmx = htmx;
        this.margin = margin;
        this.padding = padding;
        this.id = id;
        this.border = border;
        this.text_align = textAlign;
        this.background_color = backgroundColor;
    }

    public Div id(String id) {
        return new Div(
                this.content, this.htmx, this.margin, this.padding, this.border, id,
                this.text_align, this.background_color
        );
    }

    public Div content(String content) {
        return new Div(
                content, this.htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }

    public Div border(Border border) {
        return new Div(
                this.content, this.htmx, this.margin, this.padding, border, this.id,
                this.text_align, this.background_color
        );
    }

    public Div margin(Margin margin) {
        return new Div(
                this.content, this.htmx, margin, this.padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }

    public Div padding(Padding padding) {
        return new Div(
                this.content, this.htmx, this.margin, padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }

    public Div htmx(HTMX htmx) {
        return new Div(
                this.content, htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }
    public Div text_align(TextAlign text_align) {
        return new Div(
                this.content, this.htmx, this.margin, this.padding, this.border, this.id,
                text_align, this.background_color
        );
    }
    public Div background_color(Color background_color) {
        return new Div(
                this.content, this.htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, background_color
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
