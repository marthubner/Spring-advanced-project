package com.greenfox.advancedspringproject.service;

import com.greenfox.advancedspringproject.dto.MoviesListDto;
import com.greenfox.advancedspringproject.repository.MovieRepository;
import com.greenfox.advancedspringproject.util.RetrofitUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import static com.greenfox.advancedspringproject.config.Constants.*;

import java.io.IOException;

@Service
@PropertySource("classpath:application.properties")
public class MovieService {
    private MovieApi movieApi;
    private final MovieRepository movieRepository;

    public MovieService(@Value("${API_TOKEN}") String token, MovieRepository movieRepository) {
        movieApi = RetrofitUtil.createService(MovieApi.class, token);
        this.movieRepository = movieRepository;
    }

    public MoviesListDto getMovies() {
        Call<MoviesListDto> getMoviesList = movieApi.getMovies(INCLUDE_ADULT, INCLUDE_VIDEO, LANGUAGE, PAGE, SORT_BY, WITHOUT_GENRES, VOTE_COUNT);
        return getMoviesListDto(getMoviesList);
    }

    public MoviesListDto getMoviesByName(String name, int page) {
        Call<MoviesListDto> getMoviesList = movieApi.getMoviesByName(name, INCLUDE_ADULT, LANGUAGE, page);
        return getMoviesListDto(getMoviesList);
    }

    private MoviesListDto getMoviesListDto(Call<MoviesListDto> getMoviesList) {
        MoviesListDto movieList = null;
        try {
            Response<MoviesListDto> response = getMoviesList.execute();
            if (response.isSuccessful() && response.body() != null) {
                movieList = response.body();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        saveToDatabase(movieList);
        return movieList;
    }

    private void saveToDatabase(MoviesListDto moviesList) {
        movieRepository.saveAll(moviesList.results());
    }
}
