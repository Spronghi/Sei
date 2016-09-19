package com.spronghi.kiu.model;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by spronghi on 08/09/16.
 */
public class Kiuer {
    private int id;
    private String username;
    private String name;
    private String surname;
    private Date birth;
    private String telephone;
    private float feedback;
    private String favoriteCity;
    private String profileStatus;
    private String password;

    public Kiuer(){
        id=0;
        favoriteCity="";
        feedback=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }
    public String getBirthString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(birth);
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public float getFeedback() {
        return feedback;
    }

    public void setFeedback(float feedback) {
        this.feedback = feedback;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFavoriteCity() {
        return favoriteCity;
    }

    public void setFavoriteCity(String city) {
        this.favoriteCity = city;
    }
    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public void setBirth(String birth){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.birth = formatter.parse(birth);
        } catch (ParseException e) {
            Log.d("Kiuer",e.getLocalizedMessage());
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Kiuer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", telephone='" + telephone + '\'' +
                ", feedback=" + feedback +
                ", favoriteCity='" + favoriteCity + '\'' +
                ", profileStatus='" + profileStatus + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
