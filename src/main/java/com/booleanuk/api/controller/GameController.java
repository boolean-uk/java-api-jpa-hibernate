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
    private GameRepository gamesRepository;

    @GetMapping
    public List<Game> getAllUsers() {
        return this.gamesRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> createEmployee(@RequestBody Game game) {
        return new ResponseEntity<Game>(this.gamesRepository.save(game), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getgamesById(@PathVariable int id) {
        Game games = null;
        games = this.gamesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No games with that ID found")
        );

        return ResponseEntity.ok(games);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> updateEmployee(@PathVariable int id, @RequestBody Game game) {
        Game gamesToUpdate = this.gamesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existing games with that ID found")
        );
        gamesToUpdate.setTitle(game.getTitle());
        gamesToUpdate.setGenre(game.getGenre());
        gamesToUpdate.setPublisher(game.getPublisher());
        gamesToUpdate.setDeveloper(game.getDeveloper());
        gamesToUpdate.setReleaseYear(game.getReleaseYear());
        gamesToUpdate.setEarlyAccess(game.getEarlyAccess());
        return new ResponseEntity<Game>(this.gamesRepository.save(gamesToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteEmployee(@PathVariable int id) {
        Game gamesToDelete = this.gamesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existing games with that ID found")
        );

        this.gamesRepository.delete(gamesToDelete);
        return ResponseEntity.ok(gamesToDelete);
    }

}