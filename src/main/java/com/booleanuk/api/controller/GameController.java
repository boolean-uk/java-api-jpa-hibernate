package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
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
    public List<Game> getAll(){
        return this.gameRepository.findAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id){
        Game game = this.gameRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        return ResponseEntity.ok(game);
    }
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(this.gameRepository.save(game), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable int id){
        Game gameToDelete = null;
        gameToDelete = this.gameRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.gameRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable int id, @RequestBody Game game){
        Game gameToUpdate = null;
        gameToUpdate = this.gameRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }
}



