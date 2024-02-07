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
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ResponseEntity.ok(user);
    }

    //record PostUser(String email, String firstName, String lastName, String username, String phone) {}

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User request) {
        return new ResponseEntity<User>(this.userRepository.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
        User employeeToUpdate = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        employeeToUpdate.setFirstName(user.getFirstName());
        employeeToUpdate.setLastName(user.getLastName());
        employeeToUpdate.setEmail(user.getEmail());
        employeeToUpdate.setPhone(user.getPhone());
        return new ResponseEntity<User>(this.userRepository.save(employeeToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {
        User userToDelete = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        this.userRepository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }
}
