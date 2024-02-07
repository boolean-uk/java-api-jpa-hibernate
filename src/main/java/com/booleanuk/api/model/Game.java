package com.booleanuk.api.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    public String title;
    @Column(name = "genre", nullable = false)
    public String genre;
    @Column(name = "publisher")
    public String publisher;
    @Column(name = "developer")
    public String developer;
    @Column(name = "release_year")
    public Integer releaseYear;
    @Column(name = "is_early_access")
    public Boolean isEarlyAccess;

    public Game() {}

    public Game(final Integer id, final String title, final String genre, final String publisher, final String developer, final Integer releaseYear, final Boolean isEarlyAccess) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }

    public Game(final String title, final String genre, final String publisher, final String developer, final Integer releaseYear) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;

        isEarlyAccess = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game _user = (Game) o;

        return Objects.equals(id, _user.id) &&
                Objects.equals(title, _user.title) &&
                Objects.equals(genre, _user.genre) &&
                Objects.equals(publisher, _user.publisher) &&
                Objects.equals(developer, _user.developer) &&
                Objects.equals(releaseYear, _user.releaseYear) &&
                Objects.equals(isEarlyAccess, _user.isEarlyAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, publisher, developer, releaseYear, isEarlyAccess);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", title='" + title + "'" +
                ", genre='" + genre + "'" +
                ", publisher='" + publisher + "'" +
                ", developer='" + developer + "'" +
                ", releaseYear='" + releaseYear + "'" +
                ", isEarlyAccess=" + isEarlyAccess +
                '}';
    }
}
