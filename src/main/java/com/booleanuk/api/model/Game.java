package com.booleanuk.api.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "games")
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
    @Column(name = "developer")
    private String developer;
    @Column(name = "releaseYear")
    private Integer releaseYear;
    @Column(name = "isEarlyAccess")
    private Boolean isEarlyAccess;

    public Game(){

    }

    public Game(String title, String genre, String publisher, String developer, Integer releaseYar, Boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYar;
        this.isEarlyAccess = isEarlyAccess;
    }

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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYar) {
        this.releaseYear = releaseYar;
    }

    public Boolean getEarlyAccess() {
        return isEarlyAccess;
    }

    public void setEarlyAccess(Boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }
}

