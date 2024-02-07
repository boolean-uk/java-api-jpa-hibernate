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
        if(this.repository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"List is empty!");
        }
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        User user = this.repository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cant find the user"));

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(this.repository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateAnUser(@PathVariable int id,@RequestBody User user){
        User userToUpdate = this.repository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cant find the user"));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());

        return new ResponseEntity<>(this.repository.save(userToUpdate),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteAnUser(@PathVariable int id){
        User userToDelete = this.repository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cant find the user, unfortunately!"));
        this.repository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
}
