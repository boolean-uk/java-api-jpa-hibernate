package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = gameRepository.findAll();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Update game fields
        existingGame.setTitle(game.getTitle());
        existingGame.setGenre(game.getGenre());
        existingGame.setPublisher(game.getPublisher());
        existingGame.setDeveloper(game.getDeveloper());
        existingGame.setReleaseYear(game.getReleaseYear());
        existingGame.setEarlyAccess(game.isEarlyAccess());

        gameRepository.save(existingGame);
        return new ResponseEntity<>(existingGame, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
