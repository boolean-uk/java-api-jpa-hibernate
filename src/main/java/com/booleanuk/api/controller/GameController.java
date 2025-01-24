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
    private GameRepository repository;

    @PostMapping
    public ResponseEntity<Game> createGame (@RequestBody Game game) {
        return new ResponseEntity<Game>(this.repository.save(game), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Game> getAllGames() {
        return this.repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game updateGame = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find game with given id"));

        updateGame.setTitle(game.getTitle());
        updateGame.setGenre(game.getGenre());
        updateGame.setPublisher(game.getPublisher());
        updateGame.setDeveloper(game.getDeveloper());
        updateGame.setReleaseYear(game.getReleaseYear());
        updateGame.setEarlyAccess(game.isEarlyAccess());

        return new ResponseEntity<Game>(this.repository.save(updateGame), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game deleteGame = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find game with given id"));
        this.repository.delete(deleteGame);
        return ResponseEntity.ok(deleteGame);
    }
}