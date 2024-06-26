package src.html;

public sealed interface Style
        permits Border, FontFamily, Margin, Padding
{
    String toStyle();
}
