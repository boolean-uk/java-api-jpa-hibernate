package com.booleanuk.api.model;

import jakarta.persistence.*;

@Entity(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
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
    @Column(name="is_early_access")
    private boolean isEarlyAccess;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean getIsEarlyAccess() {
        return isEarlyAccess;
    }

    public void setIsEarlyAccess(boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }
}
