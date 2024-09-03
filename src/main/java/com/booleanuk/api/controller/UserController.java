package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable("id") Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    record PostUser(String email, String firstName, String lastName, String username, String phone) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody PostUser request) {
        User user = new User(request.email(), request.firstName(), request.lastName(), request.username(), request.phone());
        return this.repository.save(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public User update(@PathVariable("id") Integer id, @RequestBody PostUser request){
        Optional<User> optionalUser = this.repository.findById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEmail(request.email());
            user.setFirstName(request.firstName());
            user.setLastName(request.lastName());
            user.setUsername(request.username());
            user.setPhone(request.phone());
            return this.repository.save(optionalUser.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public User delete(@PathVariable ("id") Integer id){
        Optional<User> user = this.repository.findById(id);
        if (user.isPresent()){
            this.repository.delete(user.get());
            return user.get();
        }

        throw new ResponseStatusException(HttpStatus.OK, "User does not exist");
    }
}
