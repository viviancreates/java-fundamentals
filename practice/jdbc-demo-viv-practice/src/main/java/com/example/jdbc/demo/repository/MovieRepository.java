package com.example.jdbc.demo.repository;
import com.example.jdbc.demo.model.Movie;

import java.util.List;


public interface MovieRepository {
    List<Movie> getAll();

    /*
    Add Method – Return Object with New ID
    When saving to a DB, the add() method often looks like:
    Movie add(Movie movie);
    - You pass in a Movie without a movieId
    - The DB creates the ID
    - Return a new Movie with the ID set

    So the pattern is..
    - Pass in: incomplete object
    - Return: complete object (with DB generated fields like ID)
     */
    Movie add(Movie movie);

    //there might not be by id, and want to handle gracefully
    Optional<Movie> getById(int movieID);

}
