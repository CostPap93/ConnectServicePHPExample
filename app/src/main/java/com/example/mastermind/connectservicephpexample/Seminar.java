package com.example.mastermind.connectservicephpexample;

/**
 * Created by mastermind on 26/3/2018.
 */

public class Seminar {

    private String name;
    private String date;
    private String image;

    public Seminar(){

    }

    public Seminar(String name,String date,String image){
        this.name= name;
        this.date = date;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

