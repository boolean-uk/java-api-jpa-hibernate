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
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable final Integer id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")), HttpStatus.OK);
    }

    record PostUser(String email, String firstName, String lastName, String username, String phone) {}

    @PostMapping
    public ResponseEntity<User> create(@RequestBody PostUser request) {
        return new ResponseEntity<>(repository.save(new User(request.email, request.firstName, request.lastName, request.username, request.phone)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable final Integer id, @RequestBody final User user) {
        User _targetUser = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found."));

        _targetUser.email = user.email;
        _targetUser.firstName = user.firstName;
        _targetUser.lastName = user.lastName;
        _targetUser.username = user.username;
        _targetUser.phone = user.phone;
        _targetUser.isActive = user.isActive;

        if (_targetUser.isActive == null) _targetUser.isActive = false;

        return new ResponseEntity<>(repository.save(_targetUser), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> remove(@PathVariable final Integer id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.")), HttpStatus.OK);
    }
}
