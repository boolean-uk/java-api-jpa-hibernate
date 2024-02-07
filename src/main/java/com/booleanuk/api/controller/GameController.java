package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    record PostUser(String email, String firstName) {}

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game){
        return new ResponseEntity<>(this.repository.save(game), HttpStatus.CREATED) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id){
        Game game = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        return ResponseEntity.ok(game);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> delete(@PathVariable int id){
        Game delete = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        this.repository.delete(delete);
        return ResponseEntity.ok(delete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game game){
        Game update = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        update.setTitle(game.getTitle());
        update.setGenre(game.getGenre());
        update.setPublisher(game.getPublisher());
        update.setDeveloper(game.getDeveloper());
        update.setReleaseYear(game.getReleaseYear());
        update.setEarlyAccess(game.getIsEarlyAccess());
        return new ResponseEntity<>(this.repository.save(update), HttpStatus.CREATED);
    }

}
