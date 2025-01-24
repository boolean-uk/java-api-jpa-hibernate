package com.booleanuk.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column
    private String title;
    @Column
    private String genre;
    @Column
    private String publisher;
    @Column
    private String developer;
    @Column
    private Integer releaseYear;
    @Column
    private boolean isEarlyAccess;

    public Game(String title, String genre, String publisher, String developer, Integer releaseYear,
            boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public boolean getEarlyAccess() {
        return this.isEarlyAccess;
    }
}
