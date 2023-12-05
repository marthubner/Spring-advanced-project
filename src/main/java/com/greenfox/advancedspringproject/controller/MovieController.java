package com.greenfox.advancedspringproject.controller;

import com.greenfox.advancedspringproject.config.Constants;
import com.greenfox.advancedspringproject.model.Movie;
import com.greenfox.advancedspringproject.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Movie> index() {
        return movieService.getMovies().results();
    }

    @GetMapping("/byname")
    @ResponseBody
    public List<Movie> moviesByName(String name) {
        List<Movie> movies = new ArrayList<>();
        Movie[] mtype = {};
        movies.toArray(mtype);
        int total_pages = movieService.getMoviesByName(name, Constants.PAGE).total_pages();
        for (int i = 1; i <= total_pages; i++) {
            movies.addAll(movieService.getMoviesByName(name, i).results());
        }
        return movies;
    }
}