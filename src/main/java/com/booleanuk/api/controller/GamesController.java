package com.booleanuk.api.controller;

import com.booleanuk.api.model.Games;
import com.booleanuk.api.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GamesController {

    @Autowired
    private GamesRepository repository;


    @GetMapping
    public List<Games> getAll() {
        return this.repository.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<Games> updateUser(@PathVariable int id, @RequestBody Games games) {
        Games gamesToUpdate = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existing employee with that ID found")
        );

        gamesToUpdate.setTitle(games.getTitle());
        gamesToUpdate.setGenre(games.getGenre());
        gamesToUpdate.setPublisher(games.getPublisher());
        gamesToUpdate.setDeveloper(games.getDeveloper());
        gamesToUpdate.setReleaseYear(games.getReleaseYear());
        gamesToUpdate.setEarlyAccess(games.isEarlyAccess());

        return new ResponseEntity<>(this.repository.save(gamesToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Games> deleteUser(@PathVariable int id) {
        Games gamesToDelete = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee with that ID found")
        );
        this.repository.delete(gamesToDelete);
        return ResponseEntity.ok(gamesToDelete);
    }

    @PostMapping
    public ResponseEntity<Games> createUser(@RequestBody Games games) {
        return new ResponseEntity<>(this.repository.save(games), HttpStatus.CREATED);
    }

}
