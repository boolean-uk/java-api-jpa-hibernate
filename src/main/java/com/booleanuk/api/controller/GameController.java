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

    @GetMapping("{id}")
    public Game getById(@PathVariable("id") Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Game create(@RequestBody Game request) {
        return this.repository.save(request);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable("id") int id, @RequestBody Game request) {
        Game gameToUpdate = this.repository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        gameToUpdate.setTitle(request.getTitle());
        gameToUpdate.setGenre(request.getGenre());
        gameToUpdate.setPublisher(request.getPublisher());
        gameToUpdate.setDeveloper(request.getDeveloper());
        gameToUpdate.setReleaseYear(request.getReleaseYear());
        gameToUpdate.setEarlyAccess(request.isEarlyAccess());
        this.repository.save(gameToUpdate);
        return ResponseEntity.ok(gameToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable("id") int id) {
        Game gameToDelete = this.repository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.repository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
