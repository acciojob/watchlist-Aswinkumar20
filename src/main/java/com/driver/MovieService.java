package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {

    // this is the service file which helps us to figure out the required function:
    @Autowired
    MovieRespository movieRespository;

    //    Add a movie: POST /movies/add-movie
    //    Pass the Movie object as request body
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addMovie

    public Movie addMovie(Movie movie){
        return movieRespository.createMovie(movie);
    }


    //    Add a director: POST /movies/add-director
    //    Pass the Director object as request body
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addDirector

    public Director addDirector(Director director) {
        return movieRespository.createDirector(director);
    }



    //    Pair an existing movie and director: PUT /movies/add-movie-director-pair
    //    Pass movie name and director name as request parameters
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addMovieDirectorPair


//    public HashMap<Movie, Director> addDirectorAndMoviePair(String movieName, String directorName){
//        movieRespository.addDirectorAndMoviePair(movieName,directorName);
//    }
//
    //    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    //    Pass movie name as path parameter
    //    Return Movie object wrapped in a ResponseEntity object
    //    Controller Name - getMovieByName
    public Object getMovieByName(String name) {
        return movieRespository.getMovieByName(name);
    }


    //    Get Director by director name: GET /movies/get-director-by-name/{name}
    //    Pass director name as path parameter
    //    Return Director object wrapped in a ResponseEntity object
    //    Controller Name - getDirectorByName

    public Object getDirectorByName(String name) {
        return movieRespository.getDirectorByName(name);
    }


    //    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    //    Pass director name as path parameter
    //    Return List of movies name(List()) wrapped in a ResponseEntity object
    //    Controller Name - getMoviesByDirectorName
    public List<Movie> getAllMovies() {
        return movieRespository.getAllMovies();
    }





    //    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    //    Pass director’s name as request parameter
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - deleteDirectorByName
    public void deleteDirectorByName(String directorName) {
        movieRespository.deleteDirectorByName(directorName);
    }






    //    Delete all directors and all movies by them from the records: DELETE /movies/delete-director-by-name
    //    No params or body required
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - deleteAllDirectors
    //    (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
    public void deleteByMap(HashMap<Object, List<Object>> pair) {
        movieRespository.deleteByMap(pair);
    }

}
