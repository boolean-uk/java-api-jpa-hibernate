package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository repository;

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

//    record PostUser(String email, String firstName) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User user) {
//        User user = new User(request.getEmail(), request.getFirstName());
        return this.repository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id")
        );
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setActive(user.getActive());
        return new ResponseEntity<>(userToUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> removeUser(@PathVariable int id) {
        User user = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id")
        );
        repository.deleteById(id);
        return new ResponseEntity<>(this.repository.save(user), HttpStatus.CREATED);
    }
}
