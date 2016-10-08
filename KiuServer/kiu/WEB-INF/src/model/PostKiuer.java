package model;

import util.DateFormatter;
import util.DoubleFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by spronghi on 08/09/16.
 */
public class PostKiuer {
    private int id;
    private Kiuer kiuer;
    private Helper helper;
    private Place place;
    private LocalDateTime startDate;
    private int duration;
    private double cost;
    private boolean open;
    private float toHelperFeedback;
    private float toKiuerFeedback;


    public PostKiuer(){
        id = 0;
        open = true;
        toHelperFeedback = 0;
        toKiuerFeedback = 0;
        startDate = LocalDateTime.now();

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

    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public String getStartDateString(){
        return DateFormatter.toString(startDate, "dd-MM-yyyy HH:mm");
    }
    public void setStartDate(String startDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.startDate = LocalDateTime.parse(startDate, formatter);
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
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", cost=" + cost +
                ", open=" + open +
                ", toHelperFeedback=" + toHelperFeedback +
                ", toKiuerFeedback=" + toKiuerFeedback +
                '}';
    }
}
