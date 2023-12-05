package com.greenfox.advancedspringproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    private Long id;
    private boolean adult;
    @Column(length = 2000)
    private String overview;
    private String title;
    private String release_date;
}