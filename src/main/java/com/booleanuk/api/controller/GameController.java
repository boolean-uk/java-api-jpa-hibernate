package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import jakarta.persistence.GeneratedValue;
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
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return new ResponseEntity<Game>(this.gameRepository.save(game), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game game = null;
        game = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.getEarlyAccess());
        return new ResponseEntity<Game>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game gameToDelete = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.gameRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
