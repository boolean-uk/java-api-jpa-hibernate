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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") Integer id) {
        Game game = null;
        game = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        try {
            Game savedGame = this.repository.save(game);
            return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating game", e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable("id") Integer id, @RequestBody Game game) {
        Game existingGame = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        existingGame.setTitle(game.getTitle());
        existingGame.setGenre(game.getGenre());
        existingGame.setDeveloper(game.getDeveloper());
        existingGame.setPublisher(game.getPublisher());
        existingGame.setReleaseYear(game.getReleaseYear());
        existingGame.setIsEarlyAccess(game.getIsEarlyAccess());

        return new ResponseEntity<>(this.repository.save(existingGame), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable("id") Integer id) {
        Game existingGame = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        this.repository.delete(existingGame);
        return ResponseEntity.ok(existingGame);
    }
}
