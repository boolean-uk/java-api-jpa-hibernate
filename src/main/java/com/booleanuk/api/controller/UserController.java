package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
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

    @GetMapping
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        User user = null;
        user = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with that ID."));
        return ResponseEntity.ok(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<User>(this.repository.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User>  delete(@PathVariable("id") Integer id) {
        User deleteUser = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with that ID"));
        this.repository.delete(deleteUser);
        return ResponseEntity.ok(deleteUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable("id") Integer id, @RequestBody User updated) {
        User userToUpdate = getById(id).getBody();

        userToUpdate.setEmail(updated.getEmail());
        userToUpdate.setFirstName(updated.getFirstName());
        userToUpdate.setLastName(updated.getLastName());
        userToUpdate.setUsername(updated.getUsername());
        userToUpdate.setPhone(updated.getPhone());
        return new  ResponseEntity<User>(this.repository.save(userToUpdate), HttpStatus.CREATED);
    }
}
