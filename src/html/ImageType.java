package src.html;

public enum ImageType {
    SVG,
    PNG;

    public String toString() {
        return switch (this) {
            case SVG -> "image/svg";
            case PNG -> "image/png";
        };
    }
}
