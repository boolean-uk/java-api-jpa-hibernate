package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.repository.UserRepository;
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
    private GameRepository repository;
    @GetMapping
    public ResponseEntity<List<Game>> getAll(){
        return new ResponseEntity<>(this.repository.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable int id){
        Game temp = null;
        temp = this.repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Game with given id not found"));
        return ResponseEntity.ok(temp);
    }
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game){
        return new ResponseEntity<Game>(this.repository.save(game),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game){
        Game temp = null;
        temp = this.repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Game with given id not found"));
        temp.setDeveloper(game.getDeveloper());
        temp.setGenre(game.getGenre());
        temp.setPublisher(game.getPublisher());
        temp.setReleaseYear(game.getReleaseYear());
        temp.setIsEarlyAccess(game.getIsEarlyAccess());
        temp.setTitle(game.getTitle());
        return new ResponseEntity<Game>(this.repository.save(temp),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id){
        Game temp = this.repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Game with given id not found"));
        this.repository.delete(temp);
        return ResponseEntity.ok(temp);
    }

}
