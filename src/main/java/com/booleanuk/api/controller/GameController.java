package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("games")
public class GameController {
    private final GameRepository repository;
    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Game> getAll() {
        return this.repository.findAll();
    }
    @GetMapping("{id}")
    public Game getById(@PathVariable("id") Integer id) {
        return this.repository.findById(id).orElseThrow();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Game create(@RequestBody Game newGame) {
        return this.repository.save(newGame);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public Game update(@PathVariable("id") Integer id , @RequestBody Game updatedGame) {
        Game existingGame = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setGenre(updatedGame.getGenre());
        existingGame.setPublisher(updatedGame.getPublisher());
        existingGame.setDeveloper(updatedGame.getDeveloper());
        existingGame.setReleaseYear(updatedGame.getReleaseYear());
        existingGame.setIsEarlyAccess(updatedGame.getIsEarlyAccess());
        return this.repository.save(existingGame);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public Game delete(@PathVariable("id") Integer id ) {
        Optional<Game> returnGame = this.repository.findById(id);
        this.repository.deleteById(id);
        return returnGame.orElse(null);
    }





}
