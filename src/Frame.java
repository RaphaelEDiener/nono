package src;
import java.text.MessageFormat;

record Frame(
    int width,
    int height,
    char[] chars
) {
    public Frame(int width, int height, char[] chars) {
        assert width >= 0 : 
        MessageFormat.format("Frame width ({0}) needs to be positive", width);
        assert height >= 0 : 
        MessageFormat.format("Frame height ({0}) needs to be positive", height);
        assert width*height == chars.length : 
        MessageFormat.format("Frame data [{0}] needs to match width and height ({1}x{2})", chars.length, width, height);
        this.width = width;
        this.height = height;
        this.chars = chars;
    }
}
