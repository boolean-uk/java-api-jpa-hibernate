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
    private final UserRepository userRepository;

    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    @GetMapping
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable("id") Integer id) {
        return this.userRepository.findById(id).orElseThrow();
    }

    record PostUser(String email, String firstName) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User request) {
        User user = new User(request.getEmail(), request.getFirstName(), request.getLastName(), request.getUsername(), request.getPhone());
        return this.userRepository.save(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = this.userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")
        );

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<User>(this.userRepository.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id) {
        User userToDelete = this.userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with that id")
        );
        this.userRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
}
