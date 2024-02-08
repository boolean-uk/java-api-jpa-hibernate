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
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(containsNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new user, please check all required fields are correct.");
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = findUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User userToDelete = findUser(id);
        userRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = findUser(id);
        if(containsNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update the user, please check all required fields are correct.");
        }
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());

        return new ResponseEntity<>(userRepository.save(userToUpdate), HttpStatus.CREATED);
    }

    private boolean containsNull(User user) {
        return user.getEmail() == null || user.getFirstName() == null || user.getLastName() == null || user.getUsername() == null || user.getPhone() == null;
    }

    private User findUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No users matching that id were found"));
    }
}
