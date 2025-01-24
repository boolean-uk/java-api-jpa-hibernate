package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return this.repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game Game) {
        return new ResponseEntity<Game>(this.repository.save(Game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game GametoUpdate = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Game with this id found"));
        GametoUpdate.setTitle(game.getTitle());
        GametoUpdate.setGenre(game.getGenre());
        GametoUpdate.setPublisher(game.getPublisher());
        GametoUpdate.setDeveloper(game.getDeveloper());
        GametoUpdate.setReleaseYear(game.getReleaseYear());
        GametoUpdate.setEarlyAccess(game.getEarlyAccess());

        return new ResponseEntity<Game>(this.repository.save(GametoUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game GameToDelete = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.repository.delete(GameToDelete);
        return ResponseEntity.ok(GameToDelete);
    }

}
