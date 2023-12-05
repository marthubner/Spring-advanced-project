package com.greenfox.advancedspringproject.dto;

import com.greenfox.advancedspringproject.model.Movie;

import java.util.List;

public record MoviesListDto(Integer page, List<Movie> results, Integer total_pages) {
}
