package com.driver;


import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Repository
public class MovieRespository {
    // this repository will have the basic methods that need to be perform:


    // deafult constructor

    List<Movie> listOfMovies;
    List<Director> listOfDirectors;


    public MovieRespository() {
        this.listOfMovies = new ArrayList<>();
        this.listOfDirectors = new ArrayList<>();
    }

    public Movie createMovie(Movie movie) {
        listOfMovies.add(movie);
        return movie;
    }

    public Director createDirector(Director director) {
        listOfDirectors.add(director);
        return director;
    }

    public Object getMovieByName(String name) {
        int length = listOfMovies.size();

        for(int i=0; i<length; i++){
            if(Objects.equals(listOfMovies.get(i).getName(), name)){
                return listOfMovies.get(i);
            }
        }
        return null;
    }

    public Object getDirectorByName(String name) {
        int length = listOfDirectors.size();

        for(int i=0; i<length; i++){
            if(Objects.equals(listOfDirectors.get(i).getName(), name)){
                return listOfDirectors.get(i);
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        return listOfMovies;
    }
}
