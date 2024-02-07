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
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    @GetMapping("{id}")
    public Game getById(@PathVariable("id") Integer id) {
        return this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        try {
            return new ResponseEntity<>(this.gameRepository.save(game), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateOneGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.gameRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (game.getTitle() == null || game.getGenre() == null || game.getPublisher() == null
                || game.getDeveloper() == null || game.getReleaseYear() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());

        return new ResponseEntity<>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteOneGame(@PathVariable int id){
        Game gameToDelete = this.gameRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.gameRepository.delete(gameToDelete);
        return new ResponseEntity<>(gameToDelete, HttpStatus.ACCEPTED);
    }
}