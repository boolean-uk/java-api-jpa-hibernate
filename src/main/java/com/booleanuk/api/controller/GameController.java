package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
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

    @GetMapping("{id}")
    public Game getById(@PathVariable("id") Integer id) {
        return this.gameRepository.findById(id).orElseThrow();
    }



    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<Game>(this.gameRepository.save(game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game game){
        Game gameUpdate = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find"));
        gameUpdate.setTitle(game.getTitle());
        gameUpdate.setGenre(game.getGenre());
        gameUpdate.setPublisher(game.getPublisher());
        gameUpdate.setDeveloper(game.getDeveloper());
        gameUpdate.setReleaseYear(game.getReleaseYear());
        gameUpdate.setEarlyAccess(game.getEarlyAccess());
        return new ResponseEntity<Game>(this.gameRepository.save(gameUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> delete(@PathVariable int id){
        Game deleteGame = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find game with this id"));
        this.gameRepository.delete(deleteGame);
        return ResponseEntity.ok(deleteGame);
    }
}
