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

    @GetMapping
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        User user = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getEmail() == null|| user.getFirstName() == null || user.getLastName() == null || user.getUsername() == null || user.getPhone() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the user, please check all required fields are correct.");
        }
        return new ResponseEntity<>(this.repository.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User userToDelete = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found"));
        this.repository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (user.getEmail() == null|| user.getFirstName() == null || user.getLastName() == null || user.getUsername() == null || user.getPhone() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update the details of the user, please check all required fields are correct.");
        }
        User userToUpdate = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found"));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<>(this.repository.save(userToUpdate), HttpStatus.CREATED);
    }
}
