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
    private String email;
    private String username;
    private String password;
    private String favoriteCity;

<<<<<<< HEAD
=======


>>>>>>> 1cd9d34027cbcbcca2512b8af7d56007504a4432

    public Kiuer(){
        id=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

>>>>>>> 1cd9d34027cbcbcca2512b8af7d56007504a4432
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
<<<<<<< HEAD
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
=======
>>>>>>> 1cd9d34027cbcbcca2512b8af7d56007504a4432
    }

    public String getFavoriteCity() {
        return favoriteCity;
    }

<<<<<<< HEAD
    public void setFavoriteCity(String favoriteCity) {
        this.favoriteCity = favoriteCity;
=======
    public void setFavoriteCity(String city) {
        this.favoriteCity = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
>>>>>>> 1cd9d34027cbcbcca2512b8af7d56007504a4432
    }

    @Override
    public String toString() {
        return "Kiuer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
<<<<<<< HEAD
                ", password='" + password + '\'' +
                ", favoriteCity='" + favoriteCity + '\'' +
=======
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favoriteCity='" + favoriteCity + '\'' +

>>>>>>> 1cd9d34027cbcbcca2512b8af7d56007504a4432
                '}';
    }
}
