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
    private UserRepository usersRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return this.usersRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createEmployee(@RequestBody User user) {
        return new ResponseEntity<User>(this.usersRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable int id) {
        User employee = null;
        employee = this.usersRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No user with that ID found")
        );

        return ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = this.usersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existing users with that ID found")
        );
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<User>(this.usersRepository.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteEmployee(@PathVariable int id) {
        User userToDelete = this.usersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existing users with that ID found")
        );

        this.usersRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

}
