package com.greenfox.advancedspringproject.controller;

import com.greenfox.advancedspringproject.auth.model.AuthenticationRequest;
import com.greenfox.advancedspringproject.auth.service.AuthenticationService;
import com.greenfox.advancedspringproject.config.Constants;
import com.greenfox.advancedspringproject.model.Movie;
import com.greenfox.advancedspringproject.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest request) {
        authenticationService.authenticate(request);
        return "redirect:/movie";
    }

    @GetMapping("/movie")
    @ResponseBody
    public List<Movie> index() {
        return movieService.getMovies().results();
    }

    @GetMapping("/byname")
    @ResponseBody
    public List<Movie> moviesByName(String name) {
        List<Movie> movies = new ArrayList<>();
        int total_pages = movieService.getMoviesByName(name, Constants.PAGE).total_pages();
        for (int i = 1; i <= total_pages; i++) {
            movies.addAll(movieService.getMoviesByName(name, i).results());
        }
        return movies;
    }
}