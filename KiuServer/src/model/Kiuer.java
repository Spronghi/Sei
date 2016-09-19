package model;

/**
 * Created by spronghi on 08/09/16.
 */
public class Kiuer {
    private int id;
    private String email;
    private String username;
    private String password;
    private String favoriteCity;

    public Kiuer(){
        id=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", favoriteCity='" + favoriteCity + '\'' +
                '}';
    }
}
