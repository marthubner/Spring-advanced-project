package com.greenfox.advancedspringproject.repository;

import com.greenfox.advancedspringproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}