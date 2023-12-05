package com.greenfox.advancedspringproject.dots;

import com.greenfox.advancedspringproject.model.Movie;

import java.util.List;

public record MoviesListDto(List<Movie> results) {
}
