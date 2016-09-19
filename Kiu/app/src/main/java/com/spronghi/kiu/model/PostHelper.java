package com.spronghi.kiu.model;

import android.util.Log;

import com.spronghi.kiu.setup.DoubleFormatter;
import com.spronghi.kiu.setup.TimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by spronghi on 09/09/16.
 */
public class PostHelper {
    private int id;
    private Helper helper;
    private String title;
    private String city;
    private Date startDate;
    private Date endDate;
    private double cost;
    private boolean open;

    public PostHelper(){
        id = 0;
        open = true;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getStartDate() {
        return startDate;
    }
    public String getStartDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return formatter.format(startDate);
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setStartDate(String startDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try {
            this.startDate = formatter.parse(startDate);
        } catch (ParseException e) {
            Log.d("PostHelper",e.getLocalizedMessage());
        }
    }
    public Date getEndDate() {
        return endDate;
    }
    public String getEndDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return formatter.format(endDate);
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setEndDate(String endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try {
            this.endDate = formatter.parse(endDate);
        } catch (ParseException e) {
            Log.d("PostHelper",e.getLocalizedMessage());
        }
    }

    public double getCost() {
        return cost;
    }
    public String getCostString(){
        return "€ "+ DoubleFormatter.format(cost);
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
    public String getStartDateRequest(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
        return formatter.format(startDate);
    }
    public String getEndDateRequest(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
        return formatter.format(endDate);
    }
}
