package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private final GamesRepository gamesRepository;

    public GamesController(GamesRepository repository) {
        this.gamesRepository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return this.gamesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable int id) {
        Game game = null;
        game = this.gamesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game was not found")
        );
        return game;

    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return this.gamesRepository.save(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.gamesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game was not found")
        );

        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setRelease_year(game.getRelease_year());
        gameToUpdate.setIs_early_access(game.getIs_early_access());



        return new ResponseEntity<>(this.gamesRepository.save(gameToUpdate), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable int id) {
        Game gameToDelete = this.gamesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game was not found")
        );

        gamesRepository.delete(gameToDelete);
        //ResponseEntity.ok stuurt een status code 200 terug, met gameToDelete als value
        //In echte projecten 'return ResponseEntity.noContent().build();' gebruiken ipv .ok
        //Dit stuurt een code 204 terug , 204 = no content
        return ResponseEntity.ok(gameToDelete);
    }

}
