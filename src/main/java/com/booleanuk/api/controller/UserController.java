package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import com.booleanuk.api.validation.ValidationChecker;
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
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (ValidationChecker.checkIfNullExists(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the user,please check all required fields are correct.");
        }
        if (!ValidationChecker.isEmail(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email not correct.");

        }
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found.");
        }
        return allUsers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = null;
        user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found."));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found."));
        if (ValidationChecker.checkIfNullExists(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update the user's details,please check all required fields are correct.");
        }
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<User>(this.userRepository.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User userToDelete = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found."));
        this.userRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

}
