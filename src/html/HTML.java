package src.html;

public sealed interface HTML
        permits Body, Div, Grid, GridItem, Head, Paragraph
{
    String toHtml();
    String innerHtml();
    // TODO: String toStyle();
}
