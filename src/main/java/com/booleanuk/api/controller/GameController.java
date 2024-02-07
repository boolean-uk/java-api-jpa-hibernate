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
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable int id) {
        Game game = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        if (game.getTitle() == null|| game.getGenre() == null || game.getPublisher() == null || game.getDeveloper() == null || game.getReleaseYear() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the game, please check all required fields are correct.");
        }
        return new ResponseEntity<>(this.gameRepository.save(game), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game gameToDelete = this.gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));
        this.gameRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        if (game.getTitle() == null|| game.getGenre() == null || game.getPublisher() == null || game.getDeveloper() == null || game.getReleaseYear() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update the details of the game, please check all required fields are correct.");
        }
        Game gameToUpdate = this.gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }
}
