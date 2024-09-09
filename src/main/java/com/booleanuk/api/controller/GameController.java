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
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") Integer id){
        Game game = null;
        game = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found with that ID."));
        return ResponseEntity.ok(game);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<Game>(this.repository.save(game), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable("id") Integer id) {
        Game deleteGame = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Game not found with that ID"));
        this.repository.delete(deleteGame);
        return ResponseEntity.ok(deleteGame);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable("id") Integer id, @RequestBody Game updated) {
        Game gameToUpdate = getById(id).getBody();

        gameToUpdate.setTitle(updated.getTitle());
        gameToUpdate.setGenre(updated.getGenre());
        gameToUpdate.setPublisher(updated.getPublisher());
        gameToUpdate.setDeveloper(updated.getDeveloper());
        gameToUpdate.setReleaseYear(updated.getReleaseYear());
        gameToUpdate.setEarlyAccess(updated.isEarlyAccess());
        return new  ResponseEntity<Game>(this.repository.save(gameToUpdate), HttpStatus.CREATED);
    }
}
