package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    record PostUser(String email, String firstName, String lastName, String username, String phone) {}

    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping
    public User create(@RequestBody PostUser request) {
        User user = new User(request.email(), request.firstName(), request.lastName(), request.username(), request.phone());
        return this.repository.save(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody PostUser request) {
        User userToUpdate = this.repository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        userToUpdate.setEmail(request.email());
        userToUpdate.setFirstName(request.firstName());
        userToUpdate.setLastName(request.lastName());
        userToUpdate.setUsername(request.username());
        userToUpdate.setPhone(request.phone());
        this.repository.save(userToUpdate);
        return ResponseEntity.ok(userToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        User userToDelete = this.repository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.repository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
}
