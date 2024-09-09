package com.booleanuk.api.controller;


import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
class GameController{
	private final GameRepository repository;

	public GameController(GameRepository repository){
		this.repository = repository;
	}

	@GetMapping
	public List<Game> getGames(){
		return repository.findAll();
	}

	@PostMapping
	public ResponseEntity<Game> postGame(@RequestBody Game game){
		return new ResponseEntity<>(repository.save(game), HttpStatus.CREATED);
	}


	@PutMapping("{id}")
	public ResponseEntity<Game> updateUser(@PathVariable int id,
										   @RequestBody Game game) {
		Game gameToUpdate = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No existing games with that ID found")
		);
		gameToUpdate.setDeveloper(game.getDeveloper());
		gameToUpdate.setGenre(game.getGenre());
		gameToUpdate.setPublisher(game.getPublisher());
		gameToUpdate.setTitle(game.getTitle());
		gameToUpdate.setEarlyAccess(game.isEarlyAccess());
		gameToUpdate.setReleaseYear(game.getReleaseYear());
		return new ResponseEntity<>(repository.save(gameToUpdate),
				HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Game> deleteGame(@PathVariable int id){
		boolean isFound = repository.existsById(id);
		if (! isFound){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Game delGame = repository.findById(id).get();
		repository.delete(delGame);
		return new ResponseEntity<>(delGame, HttpStatus.OK);
	}




}