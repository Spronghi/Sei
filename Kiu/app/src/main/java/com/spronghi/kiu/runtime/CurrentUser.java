package com.spronghi.kiu.runtime;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by spronghi on 08/09/16.
 */
public class CurrentUser {
    private static Helper helper;
    private static Kiuer kiuer;
    static {

        helper = new Helper();
        helper.setName("Giorgio");
        helper.setSurname("Fiorenzo");
        helper.setUsername("Helper");
        helper.setBirth("12/10/1993");
        helper.setTelephone("3285214658");
        helper.setFavoriteCity("Lecce");
        helper.setProfileStatus("I'm the first Helper of this app!!");
        helper.setFavoriteCost(10);

        kiuer = new Kiuer();
        kiuer.setTelephone("3256547896");
        kiuer.setName("Giulio");
        kiuer.setSurname("Rapa");
        kiuer.setUsername("Kiuer");
        kiuer.setBirth("01/05/1980");
        kiuer.setProfileStatus("I'm the first Kiuer of this app!!");
        kiuer.setFavoriteCity("Lecce");
    }
    public static Kiuer getKiuer(){
        return kiuer;
    }
    public static Helper getHelper(){
        return helper;
    }
    public static boolean isKiuer(){
        return true;
    }
}
