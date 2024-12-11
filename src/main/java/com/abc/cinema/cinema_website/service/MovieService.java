package com.abc.cinema.cinema_website.service;

import com.abc.cinema.cinema_website.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Gladiator II", "Action/Adventure", "/images/Gladiator_II.jpg"));
        movies.add(new Movie("Fury", "War/Action", "/images/Fury.jpg"));
        movies.add(new Movie("The BeeKeeper", "Action/Thriller", "/images/TheBeeKeeper.jpg"));
        movies.add(new Movie("Amaran", "Action/Adventure", "/images/Amaran.jpg"));
        movies.add(new Movie("Venom: The Last Dance", "Action/Sci-fi", "/images/Venom.jpg"));
        movies.add(new Movie("Burning Betrayal", "Romance/Thriller", "/images/BurningBetrayal.jpg"));
        movies.add(new Movie("Ghostbusters: Frozen Empire", "Comedy/Fantasy", "/images/Ghostbusters.jpg"));
        movies.add(new Movie("Red One", "Action/Comedy", "/images/RedOne.jpg"));
        return movies;
    }

}