package src.html;


public final class GridItem implements HTML {

    public final String content;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;
    public final ContentWidthUnits width;
    public final int x;
    public final int y;
    public final TextAlign text_align;
    public final Color background_color;

    public GridItem() {
        this.content = "";
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border(0, WidthUnits.PX);
        this.text_align = TextAlign.LEFT;
        this.width = ContentWidthUnits.MAX_CONTENT;
        this.x = 1;
        this.y = 1;
        this.background_color = Color.TRANSPARENT;
    }

    public GridItem(final GridItem old) {
        this.content = old.content;
        this.margin = old.margin;
        this.padding = old.padding;
        this.border = old.border;
        this.id = old.id;
        this.width = old.width;
        this.x = old.x;
        this.y = old.y;
        this.text_align = old.text_align;
        this.background_color = old.background_color;
    }

    public GridItem(
            String content, Margin margin, Padding padding, Border border, String id,
            ContentWidthUnits width, int x, int y, TextAlign text_align, Color background_color
    )
    {
        this.content = content;
        this.margin = margin;
        this.padding = padding;
        this.border = border;
        this.id = id;
        this.width = width;
        this.x = x;
        this.y = y;
        this.text_align = text_align;
        this.background_color = background_color;
    }

    public GridItem content(final String content) {
        return new GridItem(
                content, this.margin, this.padding, this.border, this.id, this.width,
                this.x, this.y, this.text_align, this.background_color
        );
    }

    public GridItem margin(final Margin margin) {
        return new GridItem(
                this.content, margin, this.padding, this.border, this.id, this.width,
                this.x, this.y, this.text_align, this.background_color
        );
    }

    public GridItem padding(final Padding padding) {
        return new GridItem(
                this.content, this.margin, padding, this.border, this.id, this.width,
                this.x, this.y, this.text_align, this.background_color
        );
    }

    public GridItem border(final Border border) {
        return new GridItem(
                this.content, this.margin, this.padding, border, this.id, this.width,
                this.x, this.y, this.text_align, this.background_color
        );
    }

    public GridItem id(final String id) {
        return new GridItem(
                this.content, this.margin, this.padding, this.border, id, this.width,
                this.x, this.y, this.text_align, this.background_color
        );
    }

    public GridItem width(final ContentWidthUnits width) {
        return new GridItem(
                this.content, this.margin, this.padding, this.border, this.id, width,
                this.x, this.y, this.text_align, this.background_color
        );
    }

    public GridItem x(final int x) {
        return new GridItem(
                this.content, this.margin, this.padding, this.border, this.id, this.width,
                x, this.y, this.text_align, this.background_color
        );
    }

    public GridItem y(final int y) {
        return new GridItem(
                this.content, this.margin, this.padding, this.border, this.id, this.width,
                this.x, y, this.text_align, this.background_color
        );
    }

    public GridItem text_align(final TextAlign text_align) {
        return new GridItem(
                this.content, this.margin, this.padding, this.border, this.id, this.width,
                this.x, this.y, text_align, this.background_color
        );
    }

    public GridItem background_color(final Color background_color) {
        return new GridItem(
                this.content, this.margin, this.padding, this.border, this.id, this.width,
                this.x, this.y, this.text_align, background_color
        );
    }

    @Override
    public String toHtml() {
        return "<div style=\""
                + "grid-area:" + this.y + " / " + this.x + "; "
                + this.text_align.toStyle()
                + this.margin.toStyle()
                + this.padding.toStyle()
                + this.width.toStyle()
                + this.text_align.toStyle()
                + "background-color: " + this.background_color.rgba() + "; "
                + "\">"
                + this.content
                + "</div>";
    }

    @Override
    public String innerHtml() {
        return this.content;
    }
}
