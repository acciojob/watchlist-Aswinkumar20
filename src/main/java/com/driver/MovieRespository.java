package com.driver;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import java.util.*;


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

    public void deleteDirectorByName(String directorName) {
        for(int i=0; i<listOfDirectors.size(); i++){
            if(Objects.equals(listOfDirectors.get(i).getName(), directorName)){
                listOfDirectors.remove(listOfDirectors.get(i));
            }
        }
    }

    public void deleteByMap(HashMap<Object, List<Object>> pair) {
        // now traverse the map and delete the required data:

        for(Map.Entry<Object, List<Object>> data: pair.entrySet()){
            if(listOfDirectors.contains(data.getKey())){
                listOfDirectors.remove((data.getKey()));
            }

            List<Object> toBeDeleted = data.getValue();

            for (Object o : toBeDeleted) {
                if (listOfMovies.contains(o)) {
                    listOfMovies.remove(o);
                }
            }
        }
    }
}
