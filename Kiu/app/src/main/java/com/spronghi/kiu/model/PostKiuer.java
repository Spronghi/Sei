package com.spronghi.kiu.model;

import android.util.Log;

import com.spronghi.kiu.setup.DoubleFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by spronghi on 08/09/16.
 */
public class PostKiuer {
    private int id;
    private Kiuer kiuer;
    private Helper helper;
    private Place place;
    private Date startDate;
    private int duration;
    private double cost;
    private boolean open;
    private float toHelperFeedback;
    private float toKiuerFeedback;

    public PostKiuer(){
        id = 0;
        open = true;
        place = new Place();
        toHelperFeedback = 0;
        toKiuerFeedback = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kiuer getKiuer() {
        return kiuer;
    }

    public void setKiuer(Kiuer kiuer) {
        this.kiuer = kiuer;
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public String getStartDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        return formatter.format(startDate);
    }
    public String getStartDateRequest(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-kk-mm");
        return formatter.format(startDate);
    }
    public void setStartDate(String startDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        try {
            this.startDate = formatter.parse(startDate);
        } catch (ParseException e) {
            Log.d("PostKiuer",e.getLocalizedMessage());
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }
    public String getCostString(){
        return DoubleFormatter.format(cost);
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

    public float getToHelperFeedback() {
        return toHelperFeedback;
    }

    public void setToHelperFeedback(float toHelperFeedback) {
        this.toHelperFeedback = toHelperFeedback;
    }

    public float getToKiuerFeedback() {
        return toKiuerFeedback;
    }

    public void setToKiuerFeedback(float toKiuerFeedback) {
        this.toKiuerFeedback = toKiuerFeedback;
    }

    @Override
    public String toString() {
        return "PostKiuer{" +
                "id=" + id +
                ", kiuer=" + kiuer +
                ", helper=" + helper +
                ", place=" + place +
                ", startDate=" + getStartDateString() +
                ", duration=" + duration +
                ", cost=" + cost +
                ", open=" + open +
                ", toHelperFeedback=" + toHelperFeedback +
                ", toKiuerFeedback=" + toKiuerFeedback +
                '}';
    }
}
