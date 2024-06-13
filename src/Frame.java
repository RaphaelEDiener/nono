package src;

import java.text.MessageFormat;
import java.lang.UnsupportedOperationException;
import java.util.*;
import java.util.stream.*;

class Frame {
    final int width;
    final int height;
    final String[] chars; // why must chars be an string array? because java can't unicode


    Frame(final Frame old) {
        this(old.width, old.height, old.chars.clone());
    }

    Frame(final int width, final int height, final String[] chars) {
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

    static Frame empty(final int width, final int height) {
        assert width >= 0 : 
        MessageFormat.format("Frame width ({0}) needs to be positive", width);
        assert height >= 0 : 
        MessageFormat.format("Frame height ({0}) needs to be positive", height);
        final String[] chars = new String[width*height];
        Arrays.fill(chars, " ");
        return new Frame(width, height, chars);
    }
    
    static Frame empty(final Pair<Integer, Integer> pair) {
        return Frame.empty(pair.first, pair.second);
    }
}
