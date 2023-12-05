package com.greenfox.advancedspringproject.controller;

import com.greenfox.advancedspringproject.model.Movie;
import com.greenfox.advancedspringproject.service.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
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
        return movieService.getMoviesByName(name).results();
    }
}