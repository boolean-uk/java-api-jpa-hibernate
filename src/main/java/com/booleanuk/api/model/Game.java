package com.booleanuk.api.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "is_early_access")
    private Boolean isEarlyAccess;

    public Game(){}

    public Game(String title, String genre, String publisher, Integer releaseYear, Boolean isEarlyAccess){
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public Game(Integer id, String title, String genre, String publisher, Integer releaseYear, Boolean isEarlyAccess){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
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
                && Objects.equals(genre, game.title)
                && Objects.equals(publisher, game.publisher)
                && Objects.equals(releaseYear, game.releaseYear)
                && Objects.equals(isEarlyAccess, game.isEarlyAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, publisher, releaseYear, isEarlyAccess);
    }

    public Integer getId(){ return this.id; }

    public void setId(int id){ this.id = id; }

    public String getTitle(){ return this.title; }

    public void setTitle(String title) { this.title = title; }

    public String getGenre(){ return this.genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getPublisher() { return this.publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getReleaseYear(){ return this.releaseYear; }

    public void setReleaseYear(int releaseYear){ this.releaseYear = releaseYear; }

    public boolean getIsEarlyAccess(){ return this.isEarlyAccess; }

    public void setIsEarlyAccess(boolean isEarlyAccess){ this.isEarlyAccess = isEarlyAccess; }
}
