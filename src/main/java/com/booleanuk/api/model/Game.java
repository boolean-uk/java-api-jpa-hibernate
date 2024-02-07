package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String genre;
    private String publisher;
    private String developer;
    private int releaseYear;
    private boolean isEarlyAccess = true;

    public Game(String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.isEarlyAccess = isEarlyAccess;
    }
    public Game(){

    }
    public boolean getIsEarlyAccess() {
        return isEarlyAccess;
    }

    public void setEarlyAccess(boolean earlyAccess) {
        isEarlyAccess = earlyAccess;
    }
}
