package src.html;

public sealed interface HTML
        permits Body, Div, Grid, Head, Paragraph
{
    String toHtml();
    String innerHtml();
}
