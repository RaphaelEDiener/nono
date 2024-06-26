package src.html;

public sealed interface Meta
    permits Favicon
{
    String toHtml();
}
