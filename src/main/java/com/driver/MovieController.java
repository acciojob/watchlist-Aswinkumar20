package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieServiceObj;


    List<Movie> listOfMovies;
    List<Director> listOfDirector;
    List<Movie> moviesOfDirector;
    HashMap<Director, List<Movie>> pair;             // here list will store all the movies direct by director;
         //director  //movies

    // a single director can direct many movies not vice versa:

    public MovieController(){
        this.listOfMovies = new ArrayList<>();
        this.listOfDirector = new ArrayList<>();
        this.moviesOfDirector = new ArrayList<>();
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
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }


    //    Add a director: POST /movies/add-director
    //    Pass the Director object as request body
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addDirector
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        Director newDirector =  movieServiceObj.addDirector(director);
        listOfDirector.add(newDirector);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }



    //    Pair an existing movie and director: PUT /movies/add-movie-director-pair
    //    Pass movie name and director name as request parameters
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - addMovieDirectorPair
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName,
                                               @RequestParam("directorName") String directorName){

        // now we get those from existing data then pair it to the hashMap:
        moviesOfDirector.add(movieServiceObj.getMovieByName(movieName));
        Director new1 = movieServiceObj.getDirectorByName(directorName);
        pair.put(new1,moviesOfDirector);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }




    //    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    //    Pass movie name as path parameter
    //    Return Movie object wrapped in a ResponseEntity object
    //    Controller Name - getMovieByName
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathParam("name") String name){

        return new ResponseEntity<>(movieServiceObj.getMovieByName(name), HttpStatus.OK);
    }


    //    Get Director by director name: GET /movies/get-director-by-name/{name}
    //    Pass director name as path parameter
    //    Return Director object wrapped in a ResponseEntity object
    //    Controller Name - getDirectorByName

    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathParam("name") String name){

        return new ResponseEntity<>(movieServiceObj.getDirectorByName(name), HttpStatus.OK);
    }



    //    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    //    Pass director name as path parameter
    //    Return List of movies name(List()) wrapped in a ResponseEntity object
    //    Controller Name - getMoviesByDirectorName

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathParam("value") String directorName){
        // get all moviies that pairs with director:
        for(Map.Entry<Director, List<Movie>> data: pair.entrySet()){
            if(data.getKey().getName() == directorName){
                return new ResponseEntity<>(data.getValue(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("NULL", HttpStatus.OK);
    }


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
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("value") String directorName){

        // delete from actual director list and from the pair map:
        movieServiceObj.deleteDirectorByName(directorName);


        for(Map.Entry<Director, List<Movie>> data: pair.entrySet()){
            if(data.getKey().getName() == directorName){
                pair.remove(data);
                return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>("success" ,HttpStatus.ACCEPTED);

    }


    //    Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    //    No params or body required
    //    Return success message wrapped in a ResponseEntity object
    //    Controller Name - deleteAllDirectors
    //    (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        // passing the pair map to delete the paired data:
        movieServiceObj.deleteByMap(pair);

        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }


    @GetMapping("/get-map")
    public ResponseEntity getMap(){
        return new ResponseEntity<>(pair,HttpStatus.OK);
    }
}
