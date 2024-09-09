package com.booleanuk.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Games")
public class Game {
    @Id
    @GeneratedValue
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


    public Game(Integer id, String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public Game(){}

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

    public boolean getIsEarlyAccess() {
        return isEarlyAccess;
    }

    public void setIsEarlyAccess(boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }

    @Override
    public String toString(){
        return "Game{" +
                "id=" + this.id +
                ", title='" + this.title + '\'' +
                ", genre='" + this.genre + '\'' +
                ", publiser=" + this.publisher + '\'' +
                ", developer=" + this.developer + '\'' +
                ", releaseYear=" + this.releaseYear + '\'' +
                ", isEarlyAccess=" + this.isEarlyAccess + '\'' +
                '}';
    }
}
