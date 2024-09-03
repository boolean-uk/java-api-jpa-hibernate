package com.booleanuk.api.controller;

import java.util.List;

import com.booleanuk.api.model.User;
import com.booleanuk.api.model.UserDTO;
import com.booleanuk.api.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    User user = new User(request.email(), request.firstName());
    return this.repository.save(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("{id}")
  public User delete(@PathVariable("id") Integer id) {
    User deletedRef = this.repository.getReferenceById(id);
    User deletedCopy = new User(deletedRef.getId(), deletedRef.getEmail(), deletedRef.getFirstName(),
        deletedRef.getIsActive());
    this.repository.deleteById(id);

    return deletedCopy;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PutMapping("{id}")
  public User put(@PathVariable("id") Integer id, @RequestBody UserDTO request) {
    User toUpdateRef = this.repository.getReferenceById(id);
    toUpdateRef.setFirstName(request.firstName());
    toUpdateRef.setEmail(request.email());

    return new User(toUpdateRef.getId(), toUpdateRef.getEmail(), toUpdateRef.getFirstName(),
        toUpdateRef.getIsActive());
  }
}
