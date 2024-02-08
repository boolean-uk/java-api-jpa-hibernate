package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<User>(repository.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        User user = null;
        user = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<User>(this.repository.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {
        User userToDelete = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.repository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

}
