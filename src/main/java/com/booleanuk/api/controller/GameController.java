package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.apache.coyote.Response;
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
    public List<Game> getAll() {
        return this.repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED);
    }

    // TODO: What is the difference between ordinary Game object en ResponseEntity
    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable int id) {
        Game game = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with id "+id+" found.")
        );
        return ResponseEntity.ok(game);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game game) {
        // TODO: Refactor duplicate code
        Game originalGame = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with id "+id+" found.")
        );
        game.setId(originalGame.getId());
        return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable int id) {
        // TODO: Refactor duplicate code
        Game game = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with id "+id+" found.")
        );
        this.repository.delete(game);
        return ResponseEntity.ok(game);
    }
}
