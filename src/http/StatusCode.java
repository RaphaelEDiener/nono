package src.http;

public enum StatusCode {
    OK(200, "OK"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found");

    private final int num;
    private final String text;

    StatusCode(int num, String text) {
        this.num = num;
        this.text = text;
    }

    @Override
    public String toString() {
        return this.num + " " + this.text;
    }
}
