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
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(this.repository.findAll(),HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<User> getById(@PathVariable("id") int id) {
//        User user = repository.findById(id)
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not teachers matching that is were found"));
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }
    record PostUser(String email, String firstName, String lastName, String username, String phone) {}
    @PostMapping
    public ResponseEntity<User> create(@RequestBody PostUser request) {
        User user = new User();
        user.setEmail(request.email);
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setUserName(request.username);
        user.setPhone(request.phone);

        return new ResponseEntity<>(this.repository.save(user),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody PostUser request) {

        User userToUpdate = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        userToUpdate.setEmail(request.email);
        userToUpdate.setFirstName(request.firstName);
        userToUpdate.setLastName(request.lastName);
        userToUpdate.setUserName(request.username);
        userToUpdate.setPhone(request.phone);

        return new ResponseEntity<>(this.repository.save(userToUpdate), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {

        User userToDelete = this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.repository.delete(userToDelete);
        return new ResponseEntity<>(userToDelete, HttpStatus.CREATED);
    }
}
