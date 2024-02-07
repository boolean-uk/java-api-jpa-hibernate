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
    private GameRepository repository;

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game){
        return new ResponseEntity<Game>(repository.save(game), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Game> getAll(){
        return repository.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game game){
        Game toUpdate = getById(id);

        toUpdate.setTitle(game.getTitle());
        toUpdate.setGenre(game.getGenre());
        toUpdate.setPublisher(game.getPublisher());
        toUpdate.setDeveloper(game.getDeveloper());
        toUpdate.setReleaseYear(game.getReleaseYear());
        toUpdate.setEarlyAccess(game.isEarlyAccess());

        return new ResponseEntity<Game>(repository.save(toUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> delete(@PathVariable int id){
        Game toDelete = getById(id);
        repository.delete(toDelete);

        return ResponseEntity.ok(toDelete);
    }

    private Game getById(int id){
        return repository.
                findById(id).
                orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
