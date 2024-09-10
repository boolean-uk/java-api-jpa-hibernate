package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable("id") Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User put(@PathVariable int id, @RequestBody User user) {
        User u = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        u.setFirstName(user.getFirstName());
        u.setEmail(user.getEmail());
        u.setActive(user.getActive());
        repository.save(u);
        return u;
    }

    @DeleteMapping("{id}")
    public User delete(@PathVariable int id) {
        User u = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        repository.delete(u);
        return u;
    }

    record PostUser(String email, String firstName) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody PostUser request) {
        User user = new User(request.email(), request.firstName());
        return this.repository.save(user);
    }


}
