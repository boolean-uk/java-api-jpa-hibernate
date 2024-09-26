package com.booleanuk.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title,genre,publisher,developer;
    @Column(name = "release_year")
    private String releaseYear;
    @Column(name = "is_early_access")
    private boolean isEarlyAccess;
    public Game(){

    }

    public Game(String title, String genre, String publisher, String developer, String releaseYear, boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setIsEarlyAccess(boolean isEarlyAccess) {
        this.isEarlyAccess = isEarlyAccess;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public boolean getIsEarlyAccess() {
        return isEarlyAccess;
    }
    @Override
    public String toString(){
        String game;
        if(isEarlyAccess){
            game="Early Access ";
        }else{
            game="";
        }
        game += "Game{title: "+this.title+", genre: "+this.genre+" ,publisher: "+this.publisher+" , developer: "+this.developer+" , release year: "+this.releaseYear+"}";
        return game;
    }
}
