package src.html;

public class Div implements HTML {

    final String content;
    private int margin_top;
    private int margin_left;
    private int margin_right;
    private int margin_bottom;
    private int padding_top;
    private int padding_left;
    private int padding_right;
    private int padding_bottom;

    public Div(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "<div style=\"" +
                "margin-top: " +    this.margin_top + ";" +
                "margin-left: " +   this.margin_left + ";" +
                "margin-right: " +  this.margin_right + ";" +
                "margin-bottom: " + this.margin_bottom + ";" +
                "padding-top: " +    this.padding_top + ";" +
                "padding-left: " +   this.padding_left + ";" +
                "padding-right: " +  this.padding_right + ";" +
                "padding-bottom: " + this.padding_bottom + ";" +
                "\">" +
                this.content +
                "</div>";
    }
}
