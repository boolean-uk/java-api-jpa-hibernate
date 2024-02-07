package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String genre;
    private String publisher;
    private String developer;
    private int releaseYear;
    private boolean isEarlyAccess;
}
