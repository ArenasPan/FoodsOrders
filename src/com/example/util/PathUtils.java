package com.example.util;

/**
 * Created by Pan on 17/2/10.
 */
public class PathUtils {

    public static String getImagePath() {
        String path = PathUtils.class.getClassLoader().getResource("/").getPath().replace("WEB-INF/classes", "images");
        return path;
    }
}
