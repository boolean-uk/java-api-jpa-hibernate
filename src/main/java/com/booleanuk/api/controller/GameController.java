package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return this.repository.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        try {
            Game game;
            game = this.repository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        try {
            return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable int id, @RequestBody Game game) {
        try {
            Game updateGame = this.repository.findById(id).orElseThrow();
            updateGame.setTitle(game.getTitle());
            updateGame.setGenre(game.getGenre());
            updateGame.setPublisher(game.getPublisher());
            updateGame.setDeveloper(game.getDeveloper());
            updateGame.setReleaseYear(game.getReleaseYear());
            updateGame.setEarlyAccess(game.isEarlyAccess());
            return new ResponseEntity<>(this.repository.save(updateGame), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable int id) {
        try {
            Game gameToDelete = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
            this.repository.delete(gameToDelete);
            return ResponseEntity.ok(gameToDelete);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

}
