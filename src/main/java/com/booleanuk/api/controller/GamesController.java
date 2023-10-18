package com.booleanuk.api.controller;

import com.booleanuk.api.model.Games;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GamesController {

    @Autowired
    private GamesRepository repository;

    public GamesController(GamesRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Games> getAll(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Games getById(@PathVariable int id){
        return this.repository.findById(id).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<Games> createGames(@RequestBody Games game){
        return new ResponseEntity<Games>(this.repository.save(game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Games> updateGames(@PathVariable int id, @RequestBody Games game){
        Games gamesUpToDate = this.repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));

        gamesUpToDate.setTitle(game.getTitle());
        gamesUpToDate.setGenre(game.getGenre());
        gamesUpToDate.setPublisher(game.getPublisher());
        gamesUpToDate.setDeveloper(game.getDeveloper());
        gamesUpToDate.setReleaseYear(game.getReleaseYear());
        gamesUpToDate.setEarlyAccess(game.isEarlyAccess());

        return new ResponseEntity<Games>(this.repository.save(gamesUpToDate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Games> deleteGames(@PathVariable int id){
        Games gamesToDelete = this.repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        this.repository.delete(gamesToDelete);
        return ResponseEntity.ok(gamesToDelete);
    }

}
