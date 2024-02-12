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

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "is_early_access")
    private Boolean isEarlyAccess;

    public Game() {
    }

    public Game(Integer id, String title, String genre, String publisher, String developer, Integer releaseYear, Boolean isEarlyAccess) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
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

    public void setReleaseYear(Integer releaseYear) {
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
        return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(genre, game.genre) && Objects.equals(publisher, game.publisher) && Objects.equals(developer, game.developer) && Objects.equals(releaseYear, game.releaseYear) && Objects.equals(isEarlyAccess, game.isEarlyAccess);
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
                ", publisher='" + publisher + '\'' +
                ", developer='" + developer + '\'' +
                ", releaseYear=" + releaseYear +
                ", isEarlyAccess=" + isEarlyAccess +
                '}';
    }
}
