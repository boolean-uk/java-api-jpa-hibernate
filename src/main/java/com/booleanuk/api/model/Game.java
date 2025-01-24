package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "genre")
    private String genre;

    @Getter
    @Setter
    @Column(name = "publisher")
    private String publisher;

    @Getter
    @Setter
    @Column(name = "developer")
    private String developer;

    @Getter
    @Setter
    @Column(name = "release_year")
    private int releaseYear;

    @Getter
    @Setter
    @Column(name = "is_early_access")
    private boolean isEarlyAccess;
}
