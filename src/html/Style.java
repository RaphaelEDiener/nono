package src.html;

public sealed interface Style
permits Border, Margin, Padding {
    String toStyle();
}
