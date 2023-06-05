package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAll() {
        return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.repository.findById(id).orElse(null), HttpStatus.OK);
    }

    record PostUser(String email, String firstName) {}

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable(name="id") int id, @RequestBody Game game) {
        Game requestedGame = this.repository.findById(id).orElse(null);

        if (requestedGame == null) return null;

        requestedGame.setTitle(game.getTitle());
        requestedGame.setGenre(game.getGenre());
        requestedGame.setPublisher(game.getPublisher());
        requestedGame.setDeveloper(game.getDeveloper());
        requestedGame.setReleaseYear(game.getReleaseYear());
        requestedGame.setIsEarlyAccess(game.getIsEarlyAccess());

        return new ResponseEntity<>(this.repository.save(requestedGame), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable(name="id") int id) {
        ResponseEntity<Game> deletedUserEntity = getById(id);

        this.repository.deleteById(id);

        return deletedUserEntity;
    }
}
