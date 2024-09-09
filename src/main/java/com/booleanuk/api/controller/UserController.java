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
    private UserRepository users;

    @GetMapping
    public List<User> getAll() {
        return this.users.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = this.users.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")
                );
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User newUser) {
        return new ResponseEntity<>(this.users.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {
        User userToDelete = this.users.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")
                );
        this.users.delete(userToDelete);
        return new ResponseEntity<>(userToDelete, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User newUserData) {
        User actualUserToUpdate = this.users.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")
                );

        actualUserToUpdate.setFirstName(newUserData.getFirstName());
        actualUserToUpdate.setLastName(newUserData.getLastName());
        actualUserToUpdate.setPhone(newUserData.getPhone());
        actualUserToUpdate.setUsername(newUserData.getUsername());
        actualUserToUpdate.setEmail(newUserData.getEmail());

        return new ResponseEntity<>(this.users.save(actualUserToUpdate), HttpStatus.CREATED);
    }

}









