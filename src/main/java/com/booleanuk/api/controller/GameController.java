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
    private GameRepository games;

    @GetMapping
    public List<Game> getAll() {
        return this.games.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> get(@PathVariable("id") int id) {
        Game game = this.games.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")
                );
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game newGame) {
        return new ResponseEntity<>(this.games.save(newGame), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable int id) {
        Game gameToDelete = this.games.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")
                );
        this.games.delete(gameToDelete);
        return new ResponseEntity<>(gameToDelete, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game newGameData) {
        Game actualGameToUpdate = this.games.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")
                );

        actualGameToUpdate.setDeveloper(newGameData.getDeveloper());
        actualGameToUpdate.setGenre(newGameData.getGenre());
        actualGameToUpdate.setEarlyAccess(newGameData.isEarlyAccess());
        actualGameToUpdate.setPublisher(newGameData.getPublisher());
        actualGameToUpdate.setReleaseYear(newGameData.getReleaseYear());
        actualGameToUpdate.setTitle(newGameData.getTitle());

        return new ResponseEntity<>(this.games.save(actualGameToUpdate), HttpStatus.CREATED);
    }


}
