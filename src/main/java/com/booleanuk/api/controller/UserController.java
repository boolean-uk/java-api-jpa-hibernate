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
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<User>(this.repository.save(user),
                HttpStatus.CREATED);


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
    public ResponseEntity<User> updateUser(@PathVariable int id,
                                                   @RequestBody User user){
        User userToUpdate=this.repository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No user with that ID found")
        );
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        return new ResponseEntity<User>(this.repository.save(userToUpdate
        ), HttpStatus.CREATED);



    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User userToDelete=this.repository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No user with that ID found")
        );

        this.repository.delete(userToDelete);
        return ResponseEntity.ok(userToDelete);
    }

    record PostUser(String email, String firstName) {}
    /*
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody PostUser request) {
        User user = new User(request.email(), request.firstName());
        return this.repository.save(user);
    }

     */
}
