package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.repository.UserRepository;
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
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") int id) {
        Game game = null;
        game = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with that id was found"));
        return ResponseEntity.ok(game);
    }

    record PostUser(String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Game create(@RequestBody PostUser request) {
        Game game = new Game(request.title(), request.genre(), request.publisher(), request.developer(), request.releaseYear(), request.isEarlyAccess());
        return this.repository.save(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateUser(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with that id was found"));

        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.getIsEarlyAccess());
        return new ResponseEntity<Game>(this.repository.save(gameToUpdate), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteUser(@PathVariable int id) {
        Game gameToDelete = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with that id was found"));
        this.repository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }

}
