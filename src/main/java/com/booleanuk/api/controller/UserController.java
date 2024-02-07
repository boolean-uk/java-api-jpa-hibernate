package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = null;
        user = this.userRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getFirstName() == null || user.getLastName() == null
                || user.getUsername() == null || user.getPhone() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not create the user, please check all required fields");
        }

        return new ResponseEntity<User>(this.userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User user) {
        if (user.getEmail() == null || user.getFirstName() == null || user.getLastName() == null
                || user.getUsername() == null || user.getPhone() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not update the user's details, please check all required fields");
        }

        User userToUpdate = this.userRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found"));

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<User>(this.userRepository.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id) {
        User userToDelete = this.userRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found"));
        this.userRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

}
