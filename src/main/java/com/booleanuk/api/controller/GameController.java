package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.model.UserDTO;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class GameController {
  private final GameRepository repository;

  public GameController(GameRepository repository) {
    this.repository = repository;
  }
}
