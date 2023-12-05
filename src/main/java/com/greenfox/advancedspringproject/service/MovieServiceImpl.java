package com.greenfox.advancedspringproject.service;

import com.greenfox.advancedspringproject.dots.MoviesListDto;
import com.greenfox.advancedspringproject.model.Movie;
import com.greenfox.advancedspringproject.util.RetrofitUtil;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class MovieServiceImpl {
    private Retrofit retrofit;
    private MovieApi movieApi;

    public MovieServiceImpl() {
        retrofit = RetrofitUtil.getRetrofitInstance();
        movieApi = retrofit.create(MovieApi.class);
    }

    public MoviesListDto getMovies() {
        MoviesListDto movieList = null;
        Call<MoviesListDto> getMoviesList = movieApi.getMovies();
        try {
            Response<MoviesListDto> response = getMoviesList.execute();
            if(response.isSuccessful() && response.body() != null) {
                movieList = response.body();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }
}
