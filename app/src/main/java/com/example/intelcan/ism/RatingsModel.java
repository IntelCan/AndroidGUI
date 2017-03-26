package com.example.intelcan.ism;

/**
 * Created by Arek on 25.03.2017.
 */

public class RatingsModel {
    private String name;
    private int rating;

    public RatingsModel(String name){
        this.name = name;
        this.rating = 2;
    }

    public RatingsModel(String name, int rating){
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
