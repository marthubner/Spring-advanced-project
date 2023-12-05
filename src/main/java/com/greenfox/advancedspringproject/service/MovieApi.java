package com.greenfox.advancedspringproject.service;

import com.greenfox.advancedspringproject.dots.MoviesListDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/discover/movie")
    public Call<MoviesListDto> getMovies(
            @Query("include_adult") boolean include_adult,
            @Query("include_video") boolean include_video,
            @Query("language") String language,
            @Query("page") int page,
            @Query("sort_by") String sort_by,
            @Query("without_genres") String without_genres,
            @Query("vote_count.gte") int vote_count
//            @Query("api_key") String api_key
    );
}
