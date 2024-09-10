package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String genre;
    private String publisher;
    private String developer;
    private int releaseYear;
    private Boolean isEarlyAccess;

    public Game(String title, String genre, String publisher, String developer, int releaseYear, Boolean isEarlyAccess) {
        this.title=title;
        this.genre=genre;
        this.publisher=publisher;
        this.developer=developer;
        this.releaseYear=releaseYear;
        this.isEarlyAccess=isEarlyAccess;
    }


}
