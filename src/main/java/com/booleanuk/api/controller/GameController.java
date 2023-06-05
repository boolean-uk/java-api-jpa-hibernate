package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.validation.ValidationChecker;
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
    GameRepository gameRepository;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        if (ValidationChecker.checkIfNullExists(game)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not create the game,please check all required fields are correct.");
        }
        return new ResponseEntity<Game>(gameRepository.save(game), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Game> getAllGames() {
        List<Game> allGames = gameRepository.findAll();
        if (allGames.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No games matching that id were found.");
        }
        return allGames;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Integer id) {
        Game game = null;
        game = gameRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No games matching that id were found."));
        return ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No games matching that id were found."));
        if (ValidationChecker.checkIfNullExists(game)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update the game's details,please check all required fields are correct.");
        }
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPublisher(game.getPublisher());
        gameToUpdate.setDeveloper(game.getDeveloper());
        gameToUpdate.setReleaseYear(game.getReleaseYear());
        gameToUpdate.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<Game>(this.gameRepository.save(gameToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game gameToDelete = this.gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No games matching that id were found."));
        this.gameRepository.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
