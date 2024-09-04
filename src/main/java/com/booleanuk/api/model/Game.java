package com.booleanuk.api.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

  public Game(String title, String genre, String publisher, String developer, Integer releaseYear,
      Boolean isEarlyAccess) {
    this.title = title;
    this.genre = genre;
    this.publisher = publisher;
    this.developer = developer;
    this.releaseYear = releaseYear;
    this.isEarlyAccess = isEarlyAccess;

  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Game game = (Game) o;
    return Objects.equals(this.id, game.id)
        && Objects.equals(this.title, game.title)
        && Objects.equals(this.publisher, game.publisher)
        && Objects.equals(this.developer, game.developer)
        && Objects.equals(this.releaseYear, game.releaseYear)
        && Objects.equals(this.isEarlyAccess, game.isEarlyAccess);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.title, this.publisher, this.developer, this.releaseYear, this.isEarlyAccess);
  }
}
