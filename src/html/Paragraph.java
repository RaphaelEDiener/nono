package src.html;

@SuppressWarnings("unused")
public final class Paragraph implements HTML {

    public final String content;
    public final HTMX htmx;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;
    public final ContentWidthUnits width;
    public final FontFamily fontFamily;

    public Paragraph() {
        this.content = "";
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border();
        this.width = ContentWidthUnits.MAX_CONTENT;
        this.fontFamily = FontFamily.SYSTEM_UI;
    }

    public Paragraph(final String content) {
        this.content = content;
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border();
        this.width = ContentWidthUnits.MAX_CONTENT;
        this.fontFamily = FontFamily.SYSTEM_UI;
    }

    public Paragraph(final Paragraph old) {
        this.content = old.content;
        this.htmx = old.htmx;
        this.margin = old.margin;
        this.padding = old.padding;
        this.id = old.id;
        this.border = old.border;
        this.width = old.width;
        this.fontFamily = old.fontFamily;
    }

    public Paragraph(
            final String content,
            final HTMX htmx,
            final Margin margin,
            final Padding padding,
            final Border border,
            final String id,
            final ContentWidthUnits width,
            final FontFamily fontFamily
    )
    {
        this.content = content;
        this.htmx = htmx;
        this.margin = margin;
        this.padding = padding;
        this.id = id;
        this.border = border;
        this.width = width;
        this.fontFamily = fontFamily;
    }

    public Paragraph id(String id) {
        return new Paragraph(
                this.content,
                this.htmx,
                this.margin,
                this.padding,
                this.border,
                id,
                this.width,
                this.fontFamily
        );
    }

    public Paragraph content(String content) {
        return new Paragraph(
                content, this.htmx, this.margin, this.padding, this.border, this.id, this.width,
                this.fontFamily
        );
    }

    public Paragraph border(Border border) {
        return new Paragraph(
                this.content, this.htmx, this.margin, this.padding, border, this.id, this.width,
                this.fontFamily
        );
    }

    public Paragraph margin(Margin margin) {
        return new Paragraph(
                this.content, this.htmx, margin, this.padding, this.border, this.id, this.width,
                this.fontFamily
        );
    }

    public Paragraph padding(Padding padding) {
        return new Paragraph(
                this.content,
                this.htmx,
                this.margin,
                padding,
                this.border,
                this.id,
                this.width,
                this.fontFamily
        );
    }

    public Paragraph htmx(HTMX htmx) {
        return new Paragraph(
                this.content,
                htmx,
                this.margin,
                this.padding,
                this.border,
                this.id,
                this.width,
                this.fontFamily
        );
    }

    public Paragraph width(ContentWidthUnits width) {
        return new Paragraph(
                this.content,
                this.htmx,
                this.margin,
                this.padding,
                this.border,
                this.id,
                width,
                this.fontFamily
        );
    }

    public Paragraph fontFamily(FontFamily fontFamily) {
        return new Paragraph(
                this.content,
                this.htmx,
                this.margin,
                this.padding,
                this.border,
                this.id,
                this.width,
                fontFamily
        );
    }

    public String toHtml() {
        var id = this.id.isEmpty() ? "" : "id=\"" + this.id + "\" ";
        return "<p style=\"" +
                "width: " + this.width.toString() + "; " +
                this.border.toStyle() +
                this.margin.toStyle() +
                this.padding.toStyle() +
                this.fontFamily.toStyle() +
                "\" " +
                id +
                this.htmx.toHtml() +
                ">" +
                this.content +
                "</p>";
    }

    @Override
    public String innerHtml() {
        return this.content;
    }
}
