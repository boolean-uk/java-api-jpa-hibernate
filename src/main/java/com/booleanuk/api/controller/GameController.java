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
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Game> createGam6e(@RequestBody Game game){
        return new ResponseEntity<Game>(this.repository.save(game),
                HttpStatus.CREATED);


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
    public ResponseEntity<Game> updateGame(@PathVariable int id,
                                           @RequestBody Game game){
        Game gameToUpdate=this.repository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No game with that ID found")
        );
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        return new ResponseEntity<Game>(this.repository.save(gameToUpdate
        ), HttpStatus.CREATED);



    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id){
        Game gameToDelete=this.repository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No game with that ID found")
        );

        this.repository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
