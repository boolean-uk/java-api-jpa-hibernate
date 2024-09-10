package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public Game getById(@PathVariable("id") Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Game put(@PathVariable int id, @RequestBody Game game) {
        Game g = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        g.setDeveloper(game.getDeveloper());
        g.setGenre(game.getGenre());
        g.setPublisher(game.getPublisher());
        g.setTitle(game.getTitle());
        g.setEarlyAccess(game.isEarlyAccess());
        g.setReleaseYear(game.getReleaseYear());
        repository.save(g);
        return g;
    }

    @DeleteMapping("{id}")
    public Game delete(@PathVariable int id) {
        Game g = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        repository.delete(g);
        return g;
    }

    record PostGame(String title, String genre, String publisher, String developer, int releaseYear, boolean isEarlyAccess) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Game create(@RequestBody GameController.PostGame request) {
        Game game = new Game(request.title(), request.genre, request.publisher(), request.developer(), request.releaseYear(), request.isEarlyAccess());
        return this.repository.save(game);
    }
}
