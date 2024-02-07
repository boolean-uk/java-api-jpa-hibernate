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

    @PostMapping
    public Game create(@RequestBody Game game) {
        return this.repository.save(game);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Game> getAll() {
        return this.repository.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Game update(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setIsEarlyAccess(game.getIsEarlyAccess());

        return this.repository.save(gameToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteById(@PathVariable int id) {
        Game gameToDelete = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.repository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
