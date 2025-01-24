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

    @GetMapping
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = null;
        user = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with that id was found"));
        return ResponseEntity.ok(user);
    }

    record PostUser(String email, String firstName, String lastName, String phone) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody PostUser request) {
        User user = new User(request.email(), request.firstName(), request.lastName(), request.phone());
        return this.repository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with that id was found"));

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<User>(this.repository.save(userToUpdate), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User userToDelete = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with that id was found"));
        this.repository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

}
