package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "developer")
    private String developer;

    @Column(name = "releaseYear")
    private int releaseYear;

    @Column(name = "earlyAccess")
    private boolean earlyAccess;

}
