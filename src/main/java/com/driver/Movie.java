package com.driver;

public class Movie {
    private String name;
    private int durationInMinutes;
    private double imdbRating;

    // default constructor w/o parameter:
    public  Movie(){
        System.out.println("deafult constructor is called");
    }


    // constructor with parameter:
    public Movie(String name){
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.imdbRating = imdbRating;
    }

    // getter and setters for

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
}
