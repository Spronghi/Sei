package com.spronghi.kiu.setup;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by spronghi on 09/09/16.
 */
public enum TimeFormatter {
    INSTANCE;
    public static Date parseTime(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            Log.d("TimeFormatter",e.getLocalizedMessage());
        }
        return null;
    }
    public static String formateTimeForDuration(Calendar time){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        String timeString = formatter.format(time);

        if(!isRightFormat(timeString)){
            formatter = new SimpleDateFormat("00:mm");
            return formatter.format(time);
        } else {
            return timeString;
        }
    }
    public static String formateTimeForDuration(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        String timeString = formatter.format(time);
        Log.d("stocazzo", timeString);
        if(isRightFormat(timeString)){
            formatter = new SimpleDateFormat("00:mm");
            Log.d("stocazzo", formatter.format(time));
            return formatter.format(time);
        } else {
            return timeString;
        }
    }
    public static boolean isRightFormat(String time){
        Pattern pattern = Pattern.compile("12:*");
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
}
