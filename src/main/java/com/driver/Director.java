package com.driver;

public class Director {
    // defining the structure for ideal director:

    private String name;
    private int numberOfMovies;
    private double imdbRating;

    // default w/o paramter:

    public Director(){

    }


    // constructor with parameter:
    public Director(String name, int numberOfMovies, double imdbRating){
        this.name = name;
        this.numberOfMovies = numberOfMovies;
        this.imdbRating = imdbRating;
    }

    // getter and setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
}
