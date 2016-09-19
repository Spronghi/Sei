package util;

/**
 * Created by spronghi on 07/07/16.
 */
public enum DoubleFormatter {
    INSTANCE;
    public static String format(double num){
        return String.format("%.2f", num);
    }
}
