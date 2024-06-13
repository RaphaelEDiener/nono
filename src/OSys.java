package src;

import java.util.*;
import java.util.stream.*;

enum OSys {
    WINDOWS,
    LINUX,
    UNKNOWN;

    static OSys from_sys_prop() {
        String os_name = System.getProperty("os.name").toLowerCase();
        return switch (os_name) {
            case String name when name.contains("windows") -> OSys.WINDOWS;
            case String name when name.contains("linux") -> OSys.LINUX;
            default -> OSys.UNKNOWN;
        };
    }

    static OSys from_args(final String[] args) {
        for (var x : args) {
           System.out.println(x); 
        }
        String[] cleaned = Arrays
            .stream(args)
            .map(String::toLowerCase)
            .map(String::trim)
            .toArray(String[]::new);

        if (cleaned.length == 0) {
            return OSys.UNKNOWN;
        } else if (cleaned[0].equals("linux")) {
            return OSys.LINUX;
        } else if (cleaned[0].equals("lin")) {
            return OSys.LINUX;
        } else if (cleaned[0].equals("windows")) {
            return OSys.WINDOWS;
        } else if (cleaned[0].equals("win")) {
            return OSys.WINDOWS;
        } else {
            return OSys.UNKNOWN;
        }
    }
}
