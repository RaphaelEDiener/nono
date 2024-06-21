package src.game;

import src.html.*;

public record Cursor(
        int x,
        int y
) implements HTML
{
    @Override
    public String toHtml() {
        return this.innerHtml();
    }

    @Override
    public String innerHtml() {
        return "██";
    }
}
