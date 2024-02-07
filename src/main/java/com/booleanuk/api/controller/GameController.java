package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GamesRepository;
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
    private GamesRepository gamesRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return this.gamesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game game = this.gamesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found!"));
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return new ResponseEntity<>(this.gamesRepository.save(game), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable int id) {
        Game gameToDelete = this.gamesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        this.gamesRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.gamesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<>(this.gamesRepository.save(gameToUpdate), HttpStatus.CREATED);
    }
}
