package src.html;

public class ListItem {

    public final String content;
    public final HTMX htmx;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;
    public final TextAlign text_align;
    public final Color background_color;

    public ListItem(
            final String content,
            final HTMX htmx,
            final Margin margin,
            final Padding padding,
            final Border border,
            final String id,
            final TextAlign text_align,
            final Color background_color
    )
    {
        this.content = content;
        this.htmx = htmx;
        this.margin = margin;
        this.padding = padding;
        this.border = border;
        this.id = id;
        this.text_align = text_align;
        this.background_color = background_color;
    }

    public ListItem(
            final ListItem old
    )
    {
        this.content = old.content;
        this.htmx = old.htmx;
        this.margin = old.margin;
        this.padding = old.padding;
        this.border = old.border;
        this.id = old.id;
        this.text_align = old.text_align;
        this.background_color = old.background_color;
    }

    public ListItem()
    {
        this.content = "";
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border(0, WidthUnits.PX);
        this.text_align = TextAlign.LEFT;
        this.background_color = Color.TRANSPARENT;
    }

    public ListItem content(String content) {
        return new ListItem(
                content, this.htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }

    public ListItem htmx(HTMX htmx) {
        return new ListItem(
                content, htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }

    public ListItem margin(Margin margin) {
        return new ListItem(
                this.content, this.htmx, margin, this.padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }

    public ListItem padding(Padding padding) {
        return new ListItem(
                this.content, this.htmx, this.margin, padding, this.border, this.id,
                this.text_align, this.background_color
        );
    }

    public ListItem border(Border border) {
        return new ListItem(
                this.content, this.htmx, this.margin, this.padding, border, this.id,
                this.text_align, this.background_color
        );
    }

    public ListItem id(String id) {
        return new ListItem(
                this.content, this.htmx, this.margin, this.padding, this.border, id,
                this.text_align, this.background_color
        );
    }

    public ListItem text_align(TextAlign text_align) {
        return new ListItem(
                this.content, this.htmx, this.margin, this.padding, this.border, this.id,
                text_align, this.background_color
        );
    }

    public ListItem background_color(Color background_color) {
        return new ListItem(
                this.content, this.htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, background_color
        );
    }
}
