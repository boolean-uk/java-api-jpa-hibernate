package com.booleanuk.api.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="com/booleanuk/api/model/games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="genre")
    private String genre;

    @Column(name="publisher")
    private String publisher;

    @Column(name="developer")
    private String developer;

    @Column(name="release_year")
    private int releaseYear;

    @Column(name="is_early_acccess")
    private boolean isEarlyAccess;

    public Game(Integer id, String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public Game(String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public Game() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isEarlyAccess() {
        return isEarlyAccess;
    }

    public void setEarlyAccess(boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(genre, game.genre) && Objects.equals(publisher, game.publisher)
                && Objects.equals(releaseYear, game.releaseYear) && Objects.equals(developer, game.developer) && Objects.equals(isEarlyAccess, game.isEarlyAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, publisher, developer, releaseYear, isEarlyAccess);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher=" + publisher + '\'' +
                ", developer=" + developer + '\'' +
                ", releaseYear=" + releaseYear + '\'' +
                ", isEarlyAccess=" + isEarlyAccess + '\'' +
                '}';
    }
}
