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
    public GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<List<Game>> getAll() {
        return ResponseEntity.ok(this.gameRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") Integer id) {
        Game game = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(this.gameRepository.save(game), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable("id") Integer id, @RequestBody Game game) {
        Game gameToUpdate = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setIsEarlyAccess(game.getIsEarlyAccess());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        return new ResponseEntity<>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteUser(@PathVariable("id") Integer id) {
        Game game = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.gameRepository.delete(game);
        return ResponseEntity.ok(game);
    }
}
