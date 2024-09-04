package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.GameDTO;
import com.booleanuk.api.repository.GameRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
  public Game create(@RequestBody GameDTO request) {
    Game game = new Game(request.title(), request.genre(), request.publisher(), request.developer(),
        request.releaseYear(), request.isEarlyAccess());
    return this.repository.save(game);
  }
}
