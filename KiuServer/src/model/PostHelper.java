package model;

import util.DoubleFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by spronghi on 09/09/16.
 */
public class PostHelper {
    private int id;
    private Helper helper;
    private String title;
    private String city;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }
    public String getStartDateString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm");
        return formatter.format(startDate);
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public void setStartDate(String startDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm");
        this.startDate = LocalDateTime.parse(startDate, formatter);
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public String getEndDateString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm");
        return formatter.format(endDate);
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public void setEndDate(String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.endDate = LocalDateTime.parse(endDate, formatter);
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
}
