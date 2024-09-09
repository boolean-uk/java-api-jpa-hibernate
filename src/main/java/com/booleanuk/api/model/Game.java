package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name="Games")

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

}


