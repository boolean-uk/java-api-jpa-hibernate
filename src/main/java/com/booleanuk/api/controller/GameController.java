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
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository repository;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = this.repository.save(game);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToBeUpdated = findGame(id);

        gameToBeUpdated.setTitle(game.getTitle());
        gameToBeUpdated.setGenre(game.getGenre());
        gameToBeUpdated.setPublisher(game.getPublisher());
        gameToBeUpdated.setDeveloper(game.getDeveloper());
        gameToBeUpdated.setReleaseYear(game.getReleaseYear());
        gameToBeUpdated.setEarlyAccess(game.isEarlyAccess());

        Game updatedGame = this.repository.save(gameToBeUpdated);

        return new ResponseEntity<>(updatedGame, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game gameToBeDeleted = findGame(id);
        this.repository.deleteById(id);
        return ResponseEntity.ok(gameToBeDeleted);
    }

    private Game findGame(int id) {
        return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find game"));
    }
}
