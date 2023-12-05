package com.greenfox.advancedspringproject.service;

import com.greenfox.advancedspringproject.dots.MoviesListDto;
import com.greenfox.advancedspringproject.util.RetrofitUtil;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import static com.greenfox.advancedspringproject.config.Constants.*;

import java.io.IOException;
@Component
public class MovieServiceImpl {
    private MovieApi movieApi;

    public MovieServiceImpl() {
        movieApi = RetrofitUtil.createService(MovieApi.class, API_TOKEN);
    }

    public MoviesListDto getMovies() {
        MoviesListDto movieList = null;
        Call<MoviesListDto> getMoviesList = movieApi.getMovies(INCLUDE_ADULT, INCLUDE_VIDEO, LANGUAGE, PAGE, SORT_BY, WITHOUT_GENRES, VOTE_COUNT);
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
