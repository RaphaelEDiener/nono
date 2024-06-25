package src.html;

public class Paragraph implements HTML {
    public final String content;
    public final HTMX htmx;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;

    public Paragraph() {
        this.content = "";
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border();
    }
    public Paragraph(final String content) {
        this.content = content;
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border();
    }
    public Paragraph(final Paragraph old) {
        this.content = old.content;
        this.htmx    = old.htmx   ;
        this.margin  = old.margin ;
        this.padding = old.padding;
        this.id      = old.id     ;
        this.border  = old.border ;
    }
    public Paragraph(
        final String content,
        final HTMX htmx,
        final Margin margin,
        final Padding padding,
        final Border border,
        final String id
    ) {
        this.content = old.content;
        this.htmx    = old.htmx   ;
        this.margin  = margin ;
        this.padding = old.padding;
        this.id      = old.id     ;
        this.border  = old.border ;
    }

    public Paragraph id(String id) {
        return new Paragraph(
            this.content, this.htmx, this.margin, this.padding, this.border, id
        );
    }
    public Paragraph content(String content) {
        return new Paragraph(
            content, this.htmx, this.margin, this.padding, this.border, this.id
        );
    }
    public Paragraph border(Border border){
        return new Paragraph(
            this.content, this.htmx, this.margin, this.padding, border, this.id
        );
    }
    public Paragraph margin(Margin margin) {
        return new Paragraph(
            this.content, this.htmx, margin, this.padding, this.border, this.id
        );
    }
    public Paragraph padding(Padding padding) {
        return new Paragraph(
            this.content, this.htmx, this.margin, padding, this.border, this.id
        );
    }
    public Paragraph htmx(HTMX htmx) {
        return new Paragraph(
            this.content, htmx, this.margin, this.padding, this.border, this.id
        );
    }

    public String toHtml() {
        var id = this.id.isEmpty() ? "" : "id=\"" + this.id + "\" ";
        return "<p style=\"" +
        "width: max-content; " +  // TODO: figur out something smart for this...
        this.border.toStyle() +
        this.margin.toStyle() +
        this.padding.toStyle() +
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
