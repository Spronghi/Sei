package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by spronghi on 08/09/16.
 */
public class Kiuer {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birth;
    private String telephone;
    private String favoriteCity;

    public Kiuer(){
        favoriteCity="";
        id=0;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getBirth() {
        return birth;
    }
    public String getBirthString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(birth);
    }
    public void setBirth(LocalDate birth){
        this.birth = birth;
    }
    public void setBirth(String birth){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birth = LocalDate.parse(birth, formatter);
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

    @Override
    public String toString() {
        return "Kiuer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", telephone='" + telephone + '\'' +
                ", favoriteCity='" + favoriteCity + '\'' +
                '}';
    }
}
