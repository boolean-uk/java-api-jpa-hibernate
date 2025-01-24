package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<>(this.repository.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updateUser = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find user with given id"));

        updateUser.setUsername(user.getUsername());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());

        return new ResponseEntity<User>(this.repository.save(updateUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User deleteUser = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find user with given id"));
        this.repository.delete(deleteUser);
        return ResponseEntity.ok(deleteUser);
    }
}
