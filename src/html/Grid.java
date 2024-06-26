package src.html;

public final class Grid implements HTML {


    public String toHtml() {
        return """
                <div class="grid-container">
                  <div class="grid-item">1</div>
                </div>
                """;
    }

    public String innerHtml() {
        return "";
    }
}
