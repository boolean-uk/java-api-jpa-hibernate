package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String genre;
    private String publisher;
    private String developer;
    private int releaseYear;
    private boolean isEarlyAccess;

    public Game(String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id)
                && Objects.equals(title, game.title)
                && Objects.equals(genre, game.genre)
                && Objects.equals(publisher, game.publisher)
                && Objects.equals(developer, game.developer)
                && Objects.equals(releaseYear, game.releaseYear)
                && Objects.equals(isEarlyAccess, game.isEarlyAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, publisher, developer, releaseYear, isEarlyAccess);
    }
}
