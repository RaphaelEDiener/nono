package src.html;

public class Div implements HTML {

    private int margin_top    = 0;
    private int margin_left   = 0;
    private int margin_right  = 0;
    private int margin_bottom = 0;
    private int padding_top   = 0;
    private int padding_left  = 0;
    private int padding_right = 0;
    private int padding_bottom= 0;
    public final HTMX htmx;
    public final String content;
    private String id = "";

    public Div(String content) {
        this.content = content;
        this.htmx = new HTMX();
    }

    public Div(String content, HTMX htmx) {
        this.content = content;
        this.htmx = htmx;
    }
    public Div id(String id){
        var n = new Div(this);
        n.id = id;
        return n;
    }

    public Div(Div old){
        this.content       = old.content       ;
        this.htmx          = old.htmx          ;
        this.margin_top    = old.margin_top    ;
        this.margin_left   = old.margin_left   ;
        this.margin_right  = old.margin_right  ;
        this.margin_bottom = old.margin_bottom ;
        this.padding_top   = old.padding_top   ;
        this.padding_left  = old.padding_left  ;
        this.padding_right = old.padding_right ;
        this.padding_bottom= old.padding_bottom;
        this.id            = old.id            ;
    }

    public String toHtml() {
        var id = this.id.isEmpty() ? "" : "id=\"" + this.id + "\" ";
        return "<div style=\"" +
                "margin-top: " + this.margin_top + ";" +
                "margin-left: " + this.margin_left + ";" +
                "margin-right: " + this.margin_right + ";" +
                "margin-bottom: " + this.margin_bottom + ";" +
                "padding-top: " + this.padding_top + ";" +
                "padding-left: " + this.padding_left + ";" +
                "padding-right: " + this.padding_right + ";" +
                "padding-bottom: " + this.padding_bottom + ";\" " +
                id +
                this.htmx.toHtml() +
                ">" +
                this.content +
                "</div>";
    }

    @Override
    public String innerHtml() {
        return this.content;
    }
}
