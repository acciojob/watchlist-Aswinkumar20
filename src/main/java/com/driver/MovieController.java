package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieServiceObj;


    List<Movie> listOfMovies;
    List<Director> listOfDirector;
    List<Object> movieOfDirector;
    HashMap<Object, List<Object>> pair;             // here list will store all the movies direct by director;


    public MovieController(){
        this.listOfMovies = new ArrayList<>();
        this.listOfDirector = new ArrayList<>();
        this.movieOfDirector = new ArrayList<>();
        this.pair = new HashMap<>();
    }


    //    Add a movie: POST /movies/add-movie
    //    Pass the Movie object as request body
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addMovie

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        Movie newMovie =  movieServiceObj.addMovie(movie);
        listOfMovies.add(newMovie);
        return new ResponseEntity<>(newMovie, HttpStatus.OK);
    }


    //    Add a director: POST /movies/add-director
    //    Pass the Director object as request body
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addDirector
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        Director newDirector =  movieServiceObj.addDirector(director);
        listOfDirector.add(newDirector);
        return new ResponseEntity<>(newDirector, HttpStatus.OK);
    }



    //    Pair an existing movie and director: PUT /movies/add-movie-director-pair
    //    Pass movie name and director name as request parameters
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addMovieDirectorPair
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@PathVariable("movieName") String movieName,
                                               @PathVariable("directorName") String directorName){

        // now we get those from existing data then pair it to the hashMap:
        movieOfDirector.add(movieServiceObj.getMovieByName(movieName));
        pair.put(movieServiceObj.getDirectorByName(directorName),movieOfDirector);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }




    //    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    //    Pass movie name as path parameter
    //    Return Movie object wrapped in a ResponseEntity object
    //    Controller Name - getMovieByName
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){

        return new ResponseEntity<>(movieServiceObj.getMovieByName(name), HttpStatus.OK);
    }


    //    Get Director by director name: GET /movies/get-director-by-name/{name}
    //    Pass director name as path parameter
    //    Return Director object wrapped in a ResponseEntity object
    //    Controller Name - getDirectorByName

    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){

        return new ResponseEntity<>(movieServiceObj.getDirectorByName(name), HttpStatus.OK);
    }



    //    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    //    Pass director name as path parameter
    //    Return List of movies name(List()) wrapped in a ResponseEntity object
    //    Controller Name - getMoviesByDirectorName


    //    Get List of all movies added: GET /movies/get-all-movies
    //    No params or body required
    //    Return List of movies name(List()) wrapped in a ResponseEntity object
    //    Controller Name - findAllMovies
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){

        return new ResponseEntity<>(movieServiceObj.getAllMovies(), HttpStatus.OK);
    }
//

//    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName
//
//    Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
//            (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
//
}
