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
    private GameRepository repository;


    @GetMapping
    public List<Game> getAll() {
        return this.repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        if(checkIfValuesAreInvalid(game)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new game, please check all required fields are correct.");
        }

        return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") Integer id) {
        Game game = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        return ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateSubject(@PathVariable int id, @RequestBody Game game) {
        if(checkIfValuesAreInvalid(game)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new game, please check all required fields are correct.");
        }
        Game updatedGame = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));
        updatedGame.setDeveloper(game.getDeveloper());
        updatedGame.setGenre(game.getGenre());
        updatedGame.setPublisher(game.getPublisher());
        updatedGame.setTitle(game.getTitle());
        updatedGame.setReleaseYear(game.getReleaseYear());
        updatedGame.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<>(this.repository.save(updatedGame), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteSubjectById(@PathVariable int id) {
        Game game = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));
        this.repository.delete(game);
        return ResponseEntity.ok(game);
    }



    private boolean checkIfValuesAreInvalid(Game game) {
        if(game.getDeveloper() == null || game.getGenre() == null || game.getTitle() == null || game.getPublisher() == null) {
            return true;
        }

        return false;
    }
}
