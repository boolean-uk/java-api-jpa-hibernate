package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(gameRepository.save(game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game updatedGame) {
        Game gameToUpdate = gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found for updating"));

        // Update the game fields using BeanUtils
        BeanUtils.copyProperties(updatedGame, gameToUpdate, "id");

        return new ResponseEntity<>(gameRepository.save(gameToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        Game gameToDelete = gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found for deleting"));

        gameRepository.delete(gameToDelete);
        return ResponseEntity.ok().build();
    }
}
