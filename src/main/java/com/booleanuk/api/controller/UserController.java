package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.repository.findById(id).orElse(null), HttpStatus.OK);
    }

    record PostUser(String email, String firstName) {}

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(this.repository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable(name="id") int id, @RequestBody User user) {
        User requestedUser = this.repository.findById(id).orElse(null);

        if (requestedUser == null) return null;

        requestedUser.setFirstName(user.getFirstName());
        requestedUser.setLastName(user.getLastName());
        requestedUser.setEmail(user.getEmail());
        requestedUser.setPhone(user.getPhone());
        requestedUser.setUsername(user.getUsername());

        return new ResponseEntity<>(this.repository.save(requestedUser), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable(name="id") int id) {
        ResponseEntity<User> deletedUserEntity = getById(id);

        this.repository.deleteById(id);

        return deletedUserEntity;
    }
}
