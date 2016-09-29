package com.spronghi.kiu.kiuing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by spronghi on 28/09/16.
 */

public class KiuingUtil {
    public static boolean isStarted(Kiuing kiuing){
        Date now = new Date();
        Calendar calendarStart = Calendar.getInstance();

        calendarStart.setTime(kiuing.getPost().getStartDate());
        calendarStart.add(Calendar.MINUTE, kiuing.getPost().getDuration());

        return now.after(kiuing.getPost().getStartDate()) && now.before(calendarStart.getTime());
    }

    public static boolean isFinished(Kiuing kiuing){
        Date now = new Date();
        Calendar calendarStart = Calendar.getInstance();

        calendarStart.setTime(kiuing.getPost().getStartDate());
        calendarStart.add(Calendar.MINUTE, kiuing.getPost().getDuration());

        return now.after(calendarStart.getTime());
    }

    public static String minutesToStart(Kiuing kiuing){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("mm");

        long diff = kiuing.getPost().getStartDate().getTime() - now.getTime();

        return formatter.format(new Date(diff));
    }
}
