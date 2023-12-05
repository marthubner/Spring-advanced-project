package com.greenfox.advancedspringproject.service;

import com.greenfox.advancedspringproject.dto.MoviesListDto;
import com.greenfox.advancedspringproject.util.RetrofitUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import static com.greenfox.advancedspringproject.config.Constants.*;

import java.io.IOException;

@Component
@PropertySource("classpath:application.properties")
public class MovieServiceImpl {
    private MovieApi movieApi;

    public MovieServiceImpl(@Value("${API_TOKEN}") String token) {
        movieApi = RetrofitUtil.createService(MovieApi.class, token);
    }

    public MoviesListDto getMovies() {
        MoviesListDto movieList = null;
        Call<MoviesListDto> getMoviesList = movieApi.getMovies(INCLUDE_ADULT, INCLUDE_VIDEO, LANGUAGE, PAGE, SORT_BY, WITHOUT_GENRES, VOTE_COUNT);
        try {
            Response<MoviesListDto> response = getMoviesList.execute();
            if (response.isSuccessful() && response.body() != null) {
                movieList = response.body();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }

    public MoviesListDto getMoviesByName(String name) {
        MoviesListDto movieList = null;
        Call<MoviesListDto> getMoviesList = movieApi.getMoviesByName(name, INCLUDE_ADULT, LANGUAGE, PAGE);
        try {
            Response<MoviesListDto> response = getMoviesList.execute();
            if (response.isSuccessful() && response.body() != null) {
                movieList = response.body();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }
}
