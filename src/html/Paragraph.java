package src.html;

public class Paragraph implements HTML {

    final String content;
    private int margin_top;
    private int margin_left;
    private int margin_right;
    private int margin_bottom;
    private int padding_top;
    private int padding_left;
    private int padding_right;
    private int padding_bottom;
    public final HTMX htmx;

    public Paragraph(String content) {
        this.content = content;
        this.htmx = new HTMX();
    }

    public Paragraph(String content, HTMX htmx) {
        this.content = content;
        this.htmx = htmx;
    }

    public String toHtml() {
        return "<p style=\"" +
                "margin-top: " +    this.margin_top + ";" +
                "margin-left: " +   this.margin_left + ";" +
                "margin-right: " +  this.margin_right + ";" +
                "margin-bottom: " + this.margin_bottom + ";" +
                "padding-top: " +    this.padding_top + ";" +
                "padding-left: " +   this.padding_left + ";" +
                "padding-right: " +  this.padding_right + ";" +
                "padding-bottom: " + this.padding_bottom + ";\" " +
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
