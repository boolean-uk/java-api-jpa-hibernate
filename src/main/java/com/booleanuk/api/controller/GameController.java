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
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable final Integer id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")), HttpStatus.OK);
    }

    record PostGame(String title, String genre, String publisher, String developer, Integer releaseYear) {}

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody PostGame request) {
        return new ResponseEntity<>(repository.save(new Game(request.title, request.genre, request.publisher, request.developer, request.releaseYear)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable final Integer id, @RequestBody final Game game) {
        Game _targetGame = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found."));

        _targetGame.title = game.title;
        _targetGame.genre = game.genre;
        _targetGame.publisher = game.publisher;
        _targetGame.developer = game.developer;
        _targetGame.releaseYear = game.releaseYear;
        _targetGame.isEarlyAccess = game.isEarlyAccess;

        if (_targetGame.isEarlyAccess == null) _targetGame.isEarlyAccess = false;

        return new ResponseEntity<>(repository.save(_targetGame), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> remove(@PathVariable final Integer id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")), HttpStatus.OK);
    }
}
