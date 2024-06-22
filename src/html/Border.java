package src.html;

public record Border(int width) implements HTML {

    @Override
    public String toHtml() {
        return this.width == 0 ? "" : "border: " + this.width + "em solid; ";
    }

    @Override
    public String innerHtml() {
        return "";
    }
}
