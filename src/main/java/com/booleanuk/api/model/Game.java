package com.booleanuk.api.model;

import jakarta.persistence.*;

import java.util.Objects;

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

    public void setEarlyAccess(boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }

    public Game(Integer id, String title, String genre, String publisher, String developer,
                int releaseYear, Boolean isEarlyAccess) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public Game(String title, String genre, String publisher, String developer,
                int releaseYear, Boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public Game(int id){
        this.id = id;
    }

    public Game(){

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(title, game.title)
                && Objects.equals(genre, game.genre) && Objects.equals(publisher, game.publisher)
                && Objects.equals(developer, game.developer) && Objects.equals(releaseYear, game.releaseYear)
                && Objects.equals(isEarlyAccess, game.isEarlyAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publisher, developer, releaseYear, isEarlyAccess);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", developer='" + developer + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", isEarlyAccess=" + isEarlyAccess +
                '}';
    }
}
