package com.greenfox.advancedspringproject.controller;

import com.greenfox.advancedspringproject.config.Constants;
import com.greenfox.advancedspringproject.model.Movie;
import com.greenfox.advancedspringproject.service.MovieService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/home")
    @ResponseBody
    public String homePage(@AuthenticationPrincipal OAuth2AuthenticationToken auth2AuthenticationToken, Principal principal){

        if (auth2AuthenticationToken == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof OAuth2AuthenticationToken) {
                auth2AuthenticationToken = (OAuth2AuthenticationToken) auth;
            } else {
                return "Not authenticated";
            }
        }

        OAuth2User oauth2User = auth2AuthenticationToken.getPrincipal();
        String email = oauth2User.getAttribute("email");

        return "home " + oauth2User.getAttribute("name") + ", email: " + email;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Movie> index(@AuthenticationPrincipal OAuth2AuthenticationToken auth2AuthenticationToken) {
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