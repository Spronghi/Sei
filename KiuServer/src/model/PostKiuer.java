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
    private String title;
    private String status;
    private Place place;
    private LocalDateTime startDate;
    private int duration;
    private double cost;
    private boolean open;

    public PostKiuer(){
        id = 0;
        open = true;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "PostKiuer{" +
                "id=" + id +
                ", kiuer=" + kiuer +
                ", helper=" + helper +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", place=" + place +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", cost=" + cost +
                ", open=" + open +
                '}';
    }
}
