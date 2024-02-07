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

    //dependency injection
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        if(containsNull(game)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new game, please check all required fields are correct.");
        }
        return new ResponseEntity<>(gameRepository.save(game), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game game = findGame(id);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game gameToDelete = findGame(id);
        gameRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = findGame(id);
        if(containsNull(game)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new game, please check all required fields are correct.");
        }
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setIsEarlyAccess(game.getIsEarlyAccess());

        return new ResponseEntity<>(gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }

    private Game findGame(int id) {
        return gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));
    }

    private boolean containsNull(Game game) {
        return game.getTitle() == null || game.getGenre() == null || game.getPublisher() == null || game.getDeveloper() == null || game.getReleaseYear() == null || game.getIsEarlyAccess() == null;
    }
}
