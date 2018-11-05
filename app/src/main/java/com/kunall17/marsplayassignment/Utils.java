package com.kunall17.marsplayassignment;

public class Utils {
    public static String getFileExtension(String path) {
        int lastIndex = path.lastIndexOf(".");
        if (lastIndex < 0) return "";
        return path.substring(lastIndex);
    }
}
