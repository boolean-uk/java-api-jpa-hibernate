package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
	@Autowired
	GameRepository games;

	@GetMapping
	public List<Game> getAll() {
		return this.games.findAll();
	}

	@PostMapping
	public ResponseEntity<Game> create(@RequestBody Game game) {
		return new ResponseEntity<Game>(this.games.save(game), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<Game> getGame(@PathVariable int id) {
		Game game = games.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
		return ResponseEntity.ok(game);
	}

	@PutMapping("{id}")
	public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game game) {
		Game newGame = games.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
		newGame.setDeveloper(game.getDeveloper());
		newGame.setGenre(game.getGenre());
		newGame.setPublisher(game.getPublisher());
		newGame.setTitle(game.getTitle());
		newGame.setReleaseYear(game.getReleaseYear());
		newGame.setEarlyAccess(game.isEarlyAccess());
		return new ResponseEntity<>(newGame, HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Game> delete(@PathVariable int id) {
		Game game = games.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
		games.delete(game);
		return ResponseEntity.ok(game);
	}

}
