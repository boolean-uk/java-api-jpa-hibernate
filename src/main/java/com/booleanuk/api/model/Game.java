package com.booleanuk.api.model;

import jakarta.persistence.*;


@Entity
@Table(name="Games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String title;


    private String genre;


    private String publisher;


    private String developer;


    private Integer releaseYear;

    private boolean isEarlyAccess;

    public Game(String title, String genre, String publisher, String developer, Integer releaseYear, boolean isEarlyAccess) {

        this.title=title;
        this.genre=genre;
        this.publisher=publisher;
        this.developer=developer;
        this.releaseYear=releaseYear;
        this.isEarlyAccess=isEarlyAccess;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setEarlyAccess(boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public boolean isEarlyAccess() {
        return isEarlyAccess;
    }

    public Integer getId() {
        return id;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPublisher() {
        return publisher;
    }
}
