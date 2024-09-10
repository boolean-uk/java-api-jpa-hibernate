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


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<User> createEmployee(@RequestBody User user) {
        return new ResponseEntity<>(this.repository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,
                                               @RequestBody User user) {
        User userToUpdate = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existing user with that ID found")
        );
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUserName(user.getUserName());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<>(this.repository.save(userToUpdate),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteEmployee(@PathVariable int id) {
        User userToDelete = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No users with that ID were found")
        );
        this.repository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
}

