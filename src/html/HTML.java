package src.html;

public sealed interface HTML 
permits Body, Div, Paragraph {
    String toHtml();
    String innerHtml();
}
