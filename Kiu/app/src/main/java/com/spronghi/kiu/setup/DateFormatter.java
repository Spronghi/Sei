package com.spronghi.kiu.setup;

import android.util.Log;

import com.spronghi.kiu.runtime.CurrentPost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by spronghi on 09/09/16.
 */
public enum DateFormatter {
    INSTANCE;
    public static final String BEFORE="before";
    public static Date parseDate(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            Log.d("TimeFormatter",e.getLocalizedMessage());
        }
        return null;
    }
    public static Date parseDateTime(String timeString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm");
        try {
            return formatter.parse(timeString);
        } catch (ParseException e) {
            Log.d("TimeFormatter",e.getLocalizedMessage());
        }
        return null;
    }
    public static String toStringDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm");
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
    /*
    public static String minusNow(Date start){
        Calendar now = Calendar.getInstance();
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(start);

        if(calendarStart.before(now)) {
            return BEFORE;
        }

        calendarStart.add(Calendar.HOUR_OF_DAY, -now.get(Calendar.HOUR_OF_DAY));
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm");
        Log.d("now", formatter.format(now.get(Calendar.HOUR_OF_DAY)));
        return formatter.format(calendarStart.getTime());
    }
    */
}
