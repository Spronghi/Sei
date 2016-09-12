package com.spronghi.kiu.setup;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by spronghi on 09/09/16.
 */
public enum DateFormatter {
    INSTANCE;
    public static Date parseDate(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            Log.d("TimeFormatter",e.getLocalizedMessage());
        }
        return null;
    }
}
