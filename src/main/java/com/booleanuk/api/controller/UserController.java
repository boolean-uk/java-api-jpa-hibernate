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

    // ------------ ENDPOINTS ------------ //

    //region // POST //
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<User>(this.userRepository.save(user), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Could not create user, please check all required fields are correct."
            );
        }
    }
    //endregion

    //region // GET //
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        if(users.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No users matching that id were found."
        );
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = null;
        user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No users matching that id were found."
        ));
        return ResponseEntity.ok(user);
    }
    //endregion

    //region // PUT //
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No users matching that id were found."
        ));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());

        try {
            return new ResponseEntity<User>(this.userRepository.save(userToUpdate), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Could not update the user's details, please check all required fields are correct."
            );
        }
    }
    //endregion

    //region // DELETE //
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User userToDelete = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No users matching that id were found."
        ));
        this.userRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
    //endregion
}
