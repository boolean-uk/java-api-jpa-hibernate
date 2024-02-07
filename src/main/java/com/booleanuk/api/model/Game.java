package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
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

	@Column(name = "release_year")
	private int releaseYear;

	@Column(name = "is_early_access")
	private boolean isEarlyAccess;

	public Game( String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {
		this.title = title;
		this.genre = genre;
		this.publisher = publisher;
		this.developer = developer;
		this.releaseYear = releaseYear;
		this.isEarlyAccess = isEarlyAccess;
	}
}
