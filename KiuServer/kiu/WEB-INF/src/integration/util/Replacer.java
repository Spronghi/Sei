package integration.util;

import util.DateFormatter;

/**
 * Created by spronghi on 04/07/16.
 */
public enum Replacer {
    INSTANCE;
    private static final String REGEX = "[?]";
    public static String replaceFirst(String text, String replace){
        return text.replaceFirst(REGEX, replace);
    }
    public static String replaceFirst(String text, int replace){
        return text.replaceFirst(REGEX, Integer.toString(replace));
    }
    public static String replaceFirst(String text, double replace){
        return text.replaceFirst(REGEX, String.format("%2.2f",replace));
    }
    public static String replaceFirst(String text, java.time.LocalDate replace, String format){
        return text.replaceFirst(REGEX, DateFormatter.toString(replace, format));
    }
    public static String replaceFirst(String text, java.time.LocalDateTime replace, String format){
        return text.replaceFirst(REGEX, DateFormatter.toString(replace, format));
    }
    public static String replaceFirst(String text, boolean replace){
        return text.replaceFirst(REGEX,Boolean.toString(replace));
    }
}