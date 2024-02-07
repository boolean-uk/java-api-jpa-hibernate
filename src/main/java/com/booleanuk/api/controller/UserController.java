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
        return this.repository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        if(checkIfValuesAreInvalid(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new User, please check all required fields are correct.");
        }
        return new ResponseEntity<>(this.repository.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        User user = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteSubjectById(@PathVariable int id) {
        User subject = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user matching that id were found"));
        this.repository.delete(subject);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateSubject(@PathVariable int id, @RequestBody User user) {
        if(checkIfValuesAreInvalid(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new User, please check all required fields are correct.");
        }

        User updatedUser = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found"));
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setUsername(user.getUsername());
        return new ResponseEntity<>(this.repository.save(updatedUser), HttpStatus.CREATED);
    }




    private boolean checkIfValuesAreInvalid(User user) {
        if(user.getEmail() == null || user.getFirstName() == null || user.getUsername() == null || user.getPhone() == null) {
            return true;
        }

        return false;
    }





}
