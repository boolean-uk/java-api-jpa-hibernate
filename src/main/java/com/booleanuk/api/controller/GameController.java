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
    private GameRepository repository;

    @GetMapping
    public List<Game> getAll(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id){
        Game game = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Not found"));
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id){
        Game game = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Not found"));
        this.repository.delete(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game){
        Game existingGame = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Not found"));
        existingGame.setTitle(game.getTitle());
        existingGame.setGenre(game.getGenre());
        existingGame.setPublisher(game.getPublisher());
        existingGame.setDeveloper(game.getDeveloper());
        existingGame.setReleaseYear(game.getReleaseYear());
        existingGame.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<>(this.repository.save(existingGame), HttpStatus.OK);
    }

}
