package src.html;

import java.util.*;

public class UnorderedList {

    public final Iterable<ListItem> content;
    public final HTMX htmx;
    public final Margin margin;
    public final Padding padding;
    public final Border border;
    public final String id;
    public final TextAlign text_align;
    public final Color background_color;
    public final UnorderedListType type;

    public UnorderedList(
            final Iterable<ListItem> content,
            final HTMX htmx,
            final Margin margin,
            final Padding padding,
            final Border border,
            final String id,
            final TextAlign text_align,
            final Color background_color,
            final UnorderedListType type
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
        this.type = type;
    }

    public UnorderedList(
            final UnorderedList old
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
        this.type = old.type;
    }

    public UnorderedList() {
        this.content = new ArrayList<>();
        this.htmx = new HTMX();
        this.margin = new Margin();
        this.padding = new Padding();
        this.id = "";
        this.border = new Border(0, WidthUnits.PX);
        this.text_align = TextAlign.LEFT;
        this.background_color = Color.TRANSPARENT;
        this.type = UnorderedListType.DISC;
    }

    public UnorderedList content(Iterable<ListItem> content) {
        return new UnorderedList(
                content, this.htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, this.background_color, this.type
        );
    }
    public UnorderedList htmx(HTMX htmx) {
        return new UnorderedList(
                this.content, htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, this.background_color, this.type
        );
    }
    public UnorderedList margin(Margin margin) {
        return new UnorderedList(
                this.content, this.htmx, margin, this.padding, this.border, this.id,
                this.text_align, this.background_color, this.type
        );
    }
    public UnorderedList padding(Padding padding) {
        return new UnorderedList(
                this.content, this.htmx, this.margin, padding, this.border, this.id,
                this.text_align, this.background_color, this.type
        );
    }
    public UnorderedList border(Border border) {
        return new UnorderedList(
                this.content, this.htmx, this.margin, this.padding, border, this.id,
                this.text_align, this.background_color, this.type
        );
    }
    public UnorderedList id(String id) {
        return new UnorderedList(
                this.content, this.htmx, this.margin, this.padding, this.border, id,
                this.text_align, this.background_color, this.type
        );
    }
    public UnorderedList text_align(TextAlign text_align) {
        return new UnorderedList(
                this.content, this.htmx, this.margin, this.padding, this.border, this.id,
                text_align, this.background_color, this.type
        );
    }
    public UnorderedList background_color(Color background_color) {
        return new UnorderedList(
                this.content, this.htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, background_color, this.type
        );
    }
    public UnorderedList type(UnorderedListType type) {
        return new UnorderedList(
                this.content, this.htmx, this.margin, this.padding, this.border, this.id,
                this.text_align, this.background_color, type
        );
    }

}
