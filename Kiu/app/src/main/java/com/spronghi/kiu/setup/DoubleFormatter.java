package com.spronghi.kiu.setup;

/**
 * Created by spronghi on 09/09/16.
 */
public enum DoubleFormatter {
    INSTANCE;
    public static String format(double n){
        return String.format( "%.2f", n);
    }
}
