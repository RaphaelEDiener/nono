package src.html;

public sealed interface HTML
        permits Body, Div, Head, Paragraph
{
    String toHtml();
    String innerHtml();
}
