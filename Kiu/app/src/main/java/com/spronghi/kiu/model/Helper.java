package com.spronghi.kiu.model;

/**
 * Created by spronghi on 08/09/16.
 */
public class Helper extends Kiuer{
    private double favoriteCost;

    public Helper(){
        super();
    }

    public double getFavoriteCost(){
        return favoriteCost;
    }
    public String getFavoriteCostString(){
        return String.format( "€ %.2f/h", favoriteCost);
    }
    public void setFavoriteCost(double favoriteCost){
        this.favoriteCost = favoriteCost;
    }

    @Override
    public String toString() {
        return "Helper{" +
                "favoriteCost=" + favoriteCost +
                '}';
    }
}
