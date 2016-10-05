package com.spronghi.kiu.setup;

/**
 * Created by spronghi on 28/09/16.
 */
public class StringFormatter {
    public static String formatURL(String str) {
        return str.replace(' ', '+');
    }
    public static String formatJSON(String str) {
        return str.replace('+', ' ');
    }
}
