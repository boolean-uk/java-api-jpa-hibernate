package com.booleanuk.api.controller;

import com.booleanuk.api.exceptions.UserNotFoundException;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.repository.UserRepository;
import com.booleanuk.api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(this.userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User toUpdate = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        toUpdate.setFirstName(user.getFirstName());
        toUpdate.setLastName(user.getLastName());
        toUpdate.setUsername(user.getUsername());
        toUpdate.setEmail(user.getEmail());
        toUpdate.setPhone(user.getPhone());

        return new ResponseEntity<>(this.userRepository.save(toUpdate), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User toDelete = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        this.userRepository.delete(toDelete);
        return ResponseEntity.ok(toDelete);
    }
    @PostMapping("/{userId}/add-game/{gameId}")
    public ResponseEntity<Map<String, Object>> addGameToUser(
            @PathVariable Long userId,
            @PathVariable Long gameId) {
        User user = userService.addGameToUser(userId, gameId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Game added to user successfully");
        response.put("user", user);
        response.put("game", gameRepository.findById(gameId).orElse(null));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
