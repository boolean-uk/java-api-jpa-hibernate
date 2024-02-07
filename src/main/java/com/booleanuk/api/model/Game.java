package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
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
    private int releaseYear;
    @Column
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
        return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(genre, game.genre) && Objects.equals(publisher, game.publisher) && Objects.equals(developer, game.developer) && Objects.equals(releaseYear, game.releaseYear) && Objects.equals(isEarlyAccess, game.isEarlyAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, publisher, releaseYear, isEarlyAccess);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher=" + publisher +
                ", developer=" + developer +
                ", releaseYear=" + releaseYear +
                ", earlyAccess=" + isEarlyAccess +
                '}';
    }
}
