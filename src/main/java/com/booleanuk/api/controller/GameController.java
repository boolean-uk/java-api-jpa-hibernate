package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
    private final GameRepository gameRepository;

    public GameController(GameRepository repository) {
        this.gameRepository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    @GetMapping("{id}")
    public Game getById(@PathVariable("id") Integer id) {
        return this.gameRepository.findById(id).orElseThrow();
    }

    record PostGame(String email, String firstName) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Game create(@RequestBody Game request) {
        Game game = new Game(request.getTitle(), request.getGenre(), request.getPublisher(), request.getDeveloper() ,request.getReleaseYear(), request.isEarlyAccess());
        return this.gameRepository.save(game);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.gameRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")
        );

        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<Game>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable int id) {
        Game gameToDelete = this.gameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No game with that id")
        );
        this.gameRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
