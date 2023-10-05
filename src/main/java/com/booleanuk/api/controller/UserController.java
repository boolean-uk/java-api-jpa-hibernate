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
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository users;

    @GetMapping
    public List<User> getAllUsers() {
        return this.users.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(this.users.save(user), HttpStatus.CREATED);
    }

    private User getUser(int id) {
        return users.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = this.getUser(id);
        if (userToUpdate != null){
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setPhone(user.getPhone());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this id was not found.");
        }
        return new ResponseEntity<>(this.users.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User userToDelete = this.getUser(id);
        if (userToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this id was not found.");
        }
        this.users.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
}
