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
    public List<Game> getAllGames() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Game getOneGame(@PathVariable int id) {
        return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Game With That Id Doesn't Exist"));
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        return new ResponseEntity<>(this.repository.save(game),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id,@RequestBody Game game) {
        Game gameToUpdate = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game Not Found"));
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<> (this.repository.save(gameToUpdate),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game game = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Game Not Found"));
        this.repository.delete(game);
        return ResponseEntity.ok(game);
    }
}
