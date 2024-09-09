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
    private final UserRepository repository;

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


    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user){
        return new ResponseEntity<>( repository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        boolean isFound = repository.existsById(id);
        if (! isFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User delUser = repository.findById(id).get();
        repository.delete(delUser);
        return new ResponseEntity<>(delUser, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,
                                                   @RequestBody User user) {
        User userToUpdate = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existing users with that ID found")
        );
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        return new ResponseEntity<>(repository.save(userToUpdate),
                HttpStatus.CREATED);
    }



}
