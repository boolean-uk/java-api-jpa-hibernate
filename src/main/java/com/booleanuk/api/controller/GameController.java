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
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository games;

    @GetMapping
    public List<Game> getAllGames() {
        return this.games.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return new ResponseEntity<>(this.games.save(game), HttpStatus.CREATED);
    }

    private Game getGame(int id) {
        return games.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.getGame(id);
        if (gameToUpdate != null){
            gameToUpdate.setTitle(game.getTitle());
            gameToUpdate.setGenre(game.getGenre());
            gameToUpdate.setPublisher(game.getPublisher());
            gameToUpdate.setDeveloper(game.getDeveloper());
            gameToUpdate.setReleaseYear(game.getReleaseYear());
            gameToUpdate.setIsEarlyAccess(game.getIsEarlyAccess());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with this id was not found.");
        }
        return new ResponseEntity<>(this.games.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game gameToDelete = this.getGame(id);
        if (gameToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with this id was not found.");
        }
        this.games.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
