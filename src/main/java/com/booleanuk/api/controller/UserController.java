package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAll() {
        return this.userRepository.findAll();
    }


    record PostUser(String email, String firstName) {}

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public User create(@RequestBody PostUser request) {
//        User user = new User(request.email(), request.firstName());
//        return this.userRepository.save(user);
//    }
    @PostMapping
    public ResponseEntity<User> createEmployee(@RequestBody User user){
        return new ResponseEntity<User>(this.userRepository.save(user), HttpStatus.CREATED) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = this.userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User delete = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        this.userRepository.delete(delete);
        return ResponseEntity.ok(delete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        User update = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        update.setFirstName(user.getFirstName());
        update.setLastName(user.getLastName());
        update.setEmail(user.getEmail());
        update.setUsername(user.getUsername());
        update.setPhone(user.getPhone());
        update.setIsActive(user.getIsActive());
        return new ResponseEntity<>(this.userRepository.save(update), HttpStatus.CREATED);
    }

}
