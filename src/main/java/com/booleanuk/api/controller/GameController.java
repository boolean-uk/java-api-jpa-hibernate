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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Game> getById(@PathVariable("id") Integer id) {
        Game game = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not Found")
        );
        return ResponseEntity.ok(game);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody Game body) {
        body.setId(null);
        return this.repository.save(body);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game body) {
        return this.repository.findById(id)
                .map(game -> {
                    game.setTitle(body.getTitle());
                    game.setGenre(body.getGenre());
                    game.setDeveloper(body.getDeveloper());
                    game.setPublisher(body.getPublisher());
                    game.setEarlyAccess(body.isEarlyAccess());
                    game.setReleaseYear(body.getReleaseYear());

                    Game updated = this.repository.save(game);
                    return new ResponseEntity<>(updated, HttpStatus.CREATED);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Game> deleteGameById(@PathVariable int id) {
        return this.repository.findById(id)
                .map(game -> {
                    this.repository.delete(game);
                    return new ResponseEntity<>(game, HttpStatus.OK);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
