package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository repository;

    @GetMapping
    public List<Game> getAll() { return this.repository.findAll();}

    @GetMapping("{id}")
    public Game getById(@PathVariable("id") Integer id){
        return this.repository.findById(id).orElseThrow();
    }

    record PostGame(String title, String genre, String publisher, Integer releaseYear, Boolean isEarlyAccess){}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Game create(@RequestBody PostGame request) {
        Game game = new Game(request.title(), request.genre(), request.publisher(), request.releaseYear(), request.isEarlyAccess());
        return this.repository.save(game);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public Game update(@PathVariable("id") Integer id, @RequestBody PostGame request){
        Optional<Game> optionalGame = this.repository.findById(id);

        if (optionalGame.isPresent()){
            Game game = optionalGame.get();
            game.setTitle(request.title());
            game.setGenre(request.genre());
            game.setPublisher(request.publisher());
            game.setReleaseYear(request.releaseYear());
            game.setIsEarlyAccess(request.isEarlyAccess());
            return this.repository.save(game);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game does not exist");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public Game delete(@PathVariable("id") Integer id){
        Optional<Game> game = this.repository.findById(id);
        if (game.isPresent()){
            this.repository.delete(game.get());
            return game.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game does not exist");
    }
}
