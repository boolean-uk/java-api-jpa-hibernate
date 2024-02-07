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
    public User getUserById(@PathVariable("id") Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    // ?
    // record PostUser(String email, String firstName) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(user.getPhone()         == null
            || user.getUsername()  == null
            || user.getLastName()  == null
            || user.getEmail()     == null
            || user.getFirstName() == null)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One of more required fields are null :/"
            );
        return new ResponseEntity<User>(this.userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (user.getPhone() == null
                || user.getUsername() == null
                || user.getLastName() == null
                || user.getEmail() == null
                || user.getFirstName() == null)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One of more required fields are null :/"
            );
        User userToUpdate = this.userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "The user with that id couldn't be found")
                );
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        return new ResponseEntity<>(this.userRepository.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id)    {
        User userToDelete = this.userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "The user with that id couldn't be found")
                );
        this.userRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
}
