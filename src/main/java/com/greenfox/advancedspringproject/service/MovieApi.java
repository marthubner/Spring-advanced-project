package com.greenfox.advancedspringproject.service;

import com.greenfox.advancedspringproject.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface MovieApi {

    @GET("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=vote_average.desc&without_genres=99,10755&vote_count.gte=200&api_key=2e7dc55c40e96fb38896a8e98e83d413")
    public Call<List<Movie>> getMovies();
}
