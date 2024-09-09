package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
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

    public GameController(GameRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game){
        return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Game> getAll(){
        return this.repository.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game game){
        Game gameToUpdate = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existing game with that ID found")
        );
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setIsEarlyAccess(game.getIsEarlyAccess());
        return new ResponseEntity<>(this.repository.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable int id){
        Game gameToDelete = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No game with that ID found")
        );
        this.repository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
