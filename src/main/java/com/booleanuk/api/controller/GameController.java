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
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game game = null;
        game = this.gameRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        if (game.getTitle() == null || game.getGenre() == null || game.getPublisher() == null
                || game.getDeveloper() == null || game.getReleaseYear() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not create the game, please check all required fields");
        }

        return new ResponseEntity<Game>(this.gameRepository.save(game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable int id, @RequestBody Game game) {
        if (game.getTitle() == null || game.getGenre() == null || game.getPublisher() == null
                || game.getDeveloper() == null || game.getReleaseYear() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not update the game's details, please check all required fields");
        }

        Game gameToUpdate = this.gameRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));

        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<Game>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable int id) {
        Game gameToDelete = this.gameRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found"));
        this.gameRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}