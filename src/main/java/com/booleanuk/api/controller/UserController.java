package com.booleanuk.api.controller;

import java.util.List;
import java.util.Optional;

import com.booleanuk.api.model.User;
import com.booleanuk.api.model.UserDTO;
import com.booleanuk.api.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserRepository repository;

  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<User> getAll() {
    return this.repository.findAll();
  }

  @GetMapping("{id}")
  public User getById(@PathVariable("id") Integer id) {
    return this.repository.findById(id).orElseThrow();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public User create(@RequestBody UserDTO request) {
    User user = new User(request.email(), request.firstName(), request.lastName(), request.username(), request.phone());
    return this.repository.save(user);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PutMapping("{id}")
  public User put(@PathVariable("id") Integer id, @RequestBody UserDTO request) throws ResponseStatusException {
    Optional<User> maybeUserToUpdate = this.repository.findById(id);
    if (maybeUserToUpdate.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    User userToUpdate = maybeUserToUpdate.get();
    userToUpdate.setEmail(request.email());
    userToUpdate.setFirstName(request.firstName());
    userToUpdate.setLastName(request.lastName());
    userToUpdate.setUsername(request.username());
    userToUpdate.setPhone(request.phone());

    return this.repository.save(userToUpdate);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("{id}")
  public User delete(@PathVariable("id") Integer id) {
    Optional<User> maybeUserToUpdate = this.repository.findById(id);
    if (maybeUserToUpdate.isEmpty())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    this.repository.deleteById(id);

    return maybeUserToUpdate.get();
  }
}
