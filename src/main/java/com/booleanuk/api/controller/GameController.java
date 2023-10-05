package com.booleanuk.api.controller;

import com.booleanuk.api.exceptions.GameNotFoundException;
import com.booleanuk.api.exceptions.UserNotFoundException;
import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GameRepository;

import com.booleanuk.api.repository.UserRepository;
import com.booleanuk.api.service.GameService;
import jakarta.transaction.Transactional;
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
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found.")));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return new ResponseEntity<>(this.gameRepository.save(game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game toUpdate = this.gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found."));
        toUpdate.setTitle(game.getTitle());
        toUpdate.setGenre(game.getGenre());
        toUpdate.setPublisher(game.getPublisher());
        toUpdate.setDeveloper(game.getDeveloper());
        toUpdate.setReleaseYear(game.getReleaseYear());
        toUpdate.setEarlyAccess(game.isEarlyAccess());

        return new ResponseEntity<>(this.gameRepository.save(toUpdate), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable Long id) {
        Game toDelete = this.gameRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found."));
        this.gameRepository.delete(toDelete);
        return ResponseEntity.ok(toDelete);
    }

}
