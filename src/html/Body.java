package src.html;

public class Body implements HTML {

    final String content;

    public Body(String content) {
        this.content = content;
    }

    public String toHtml() {
        return """
                <!doctype html>
                <html lang="en-US">
                <head>
                  <meta charset="UTF-8" />
                  <meta name="viewport" content="width=device-width" />
                  <title>Nonogram</title>
                </head>
                <script src="https://unpkg.com/htmx.org@2.0.0"></script>
                <body>
                %s
                </body>
                </html>
                """.formatted(content);
    }

    @Override
    public String innerHtml() {
        return this.content;
    }
}
