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
        <body>
        %s
        </body>
        </html>
        """.formatted(content);
    }
}
