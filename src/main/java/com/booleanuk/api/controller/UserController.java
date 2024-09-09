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
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        User user = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not Found")
        );
        return ResponseEntity.ok(user);
    }

    // record PostUser(String email, String firstName) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User body) {
        body.setId(null);
        return this.repository.save(body);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User body) {
        return this.repository.findById(id)
                .map(user -> {
                    user.setActive(body.getActive());
                    user.setEmail(body.getEmail());
                    user.setFirstName(body.getFirstName());
                    user.setLastName(body.getLastName());
                    user.setUsername(body.getUsername());
                    user.setPhone(body.getPhone());

                    User updated = this.repository.save(user);
                    return new ResponseEntity<>(updated, HttpStatus.CREATED);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> deleteUserById(@PathVariable int id) {
        return this.repository.findById(id)
                .map(user -> {
                    this.repository.delete(user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
