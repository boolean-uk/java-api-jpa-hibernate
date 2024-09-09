package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable("id") Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    record PostUser(String email, String firstName) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody PostUser request) {
        User user = new User(request.email(), request.firstName());
        return this.repository.save(user);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public User update(@PathVariable("id") Integer id , @RequestBody PostUser request) {
        User existingUser = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setEmail(request.email());
        existingUser.setFirstName(request.firstName());
        return this.repository.save(existingUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public User delete(@PathVariable("id") Integer id ) {
       Optional<User> returnUser = this.repository.findById(id);
       this.repository.deleteById(id);
        return returnUser.orElse(null);
    }
}
