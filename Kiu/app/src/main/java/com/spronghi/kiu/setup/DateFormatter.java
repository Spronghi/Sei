package com.spronghi.kiu.setup;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public enum DateFormatter {
    INSTANCE;
    public static final String BEFORE="before";
    public static final String AFTER="after";

    public static Date parseDate(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            Log.d("TimeFormatter",e.getLocalizedMessage());
        }
        return null;
    }
    public static Date parseDateTime(String timeString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        try {
            return formatter.parse(timeString);
        } catch (ParseException e) {
            Log.d("TimeFormatter",e.getLocalizedMessage());
        }
        return null;
    }
    public static String toStringDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
    public static String minusNow(Date start){
        Date now = new Date();

        if(start.before(now)) {
            return BEFORE;
        }
        long diff = start.getTime() - now.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        return formatter.format(new Date(diff));
    }
    public static String minusNow(Date start, int duration){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MINUTE, duration);
        if(calendar.before(now)) {
            return BEFORE;
        }
        return AFTER;
    }
}
