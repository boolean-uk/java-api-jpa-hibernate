package com.booleanuk.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String title;
    private Integer id;
    private String genre;
    private String publisher;
    private String developer;
    private int releaseYear;
    private Boolean isEarlyAccess;
    public Game(int id, String title, String genre, String publisher, String developer, int releaseYear, Boolean isEarlyAccess) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }
    public Game(String title, String genre, String publisher, String developer, int releaseYear, Boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
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

    public Boolean getEarlyAccess() {
        return isEarlyAccess;
    }

    public void setEarlyAccess(Boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(title, game.title) && Objects.equals(genre, game.genre) && Objects.equals(publisher, game.publisher) && Objects.equals(developer, game.developer) && Objects.equals(releaseYear, game.releaseYear) && Objects.equals(isEarlyAccess, game.isEarlyAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, publisher, developer, releaseYear, isEarlyAccess);
    }

    @Override
    public String toString() {
        return "Game{" +
                "title=" + title +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", developer=" + developer +
                ", releaseYear=" + releaseYear + '\'' +
                ", is Early Access=" + isEarlyAccess +
                '}';
    }
}
