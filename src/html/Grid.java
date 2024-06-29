package src.html;

import java.util.*;
import java.util.stream.*;

public final class Grid implements HTML {

    public final List<GridItem> content;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;
    public final ContentWidthUnits width;

    public Grid() {
        this.content = new ArrayList<>();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border(0, WidthUnits.PX);
        this.width = ContentWidthUnits.MAX_CONTENT;
    }

    public Grid(
            final List<GridItem> content,
            final Margin margin,
            final Padding padding,
            final Border border,
            final String id,
            final ContentWidthUnits width
    )
    {
        this.content = content;
        this.margin = margin;
        this.padding = padding;
        this.border = border;
        this.id = id;
        this.width = width;
    }

    public Grid(final Grid old) {
        this.content = old.content.stream().map(GridItem::new).toList();
        this.margin = old.margin;
        this.padding = old.padding;
        this.border = old.border;
        this.id = old.id;
        this.width = old.width;
    }

    public String toHtml() {
        var id = this.id.isEmpty() ? "" : "id=\"" + this.id + "\"";
        String content = this.content
                .stream()
                .map(GridItem::toHtml)
                .collect(Collectors.joining());
        return "<div style=\"display: grid; "
                + this.border.toStyle()
                + this.width.toStyle()
                + this.margin.toStyle()
                + this.padding.toStyle() + "\" "
                + id
                + "> "
                + content
                + "</div>";
    }

    public String innerHtml() {
        return "";
    }
}
