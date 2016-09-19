package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by spronghi on 04/07/16.
 */
public enum DateFormatter {
    INSTANCE;
    public static LocalDateTime toLocalDateTime(LocalDate date, LocalTime time){
        return LocalDateTime.of(date,time);
    }
    public static String toString(LocalDate date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
    public static String toString(LocalDateTime date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
    public static LocalDateTime fromRequest(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        return LocalDateTime.parse(date, formatter);
    }
}