package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.GameDTO;
import com.booleanuk.api.repository.GameRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Game create(@RequestBody GameDTO request) {
    Game game = new Game(request.title(), request.genre(), request.publisher(), request.developer(),
        request.releaseYear(), request.isEarlyAccess());

    return this.repository.save(game);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Game put(@PathVariable("id") Integer id, @RequestBody GameDTO request) throws ResponseStatusException {
    Optional<Game> maybeGameToUpdate = this.repository.findById(id);
    if (maybeGameToUpdate.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    Game gameToUpdate = maybeGameToUpdate.get();
    gameToUpdate.setTitle(request.title());
    gameToUpdate.setGenre(request.genre());
    gameToUpdate.setPublisher(request.publisher());
    gameToUpdate.setDeveloper(request.developer());
    gameToUpdate.setReleaseYear(request.releaseYear());
    gameToUpdate.setIsEarlyAccess(request.isEarlyAccess());

    return this.repository.save(gameToUpdate);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Game delete(@PathVariable("id") Integer id) throws ResponseStatusException {
    Optional<Game> maybeGameToDelete = this.repository.findById(id);
    if (maybeGameToDelete.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    this.repository.deleteById(id);

    return maybeGameToDelete.get();
  }
}
