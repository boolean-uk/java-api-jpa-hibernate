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
	private UserRepository repository;


	@GetMapping
	public List<User> getAll() {
		return this.repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
		User user = null;
		user = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		return new ResponseEntity<User>(this.repository.save(user), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
		User newUser = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
		newUser.setEmail(user.getEmail());
		newUser.setActive(user.getActive());
		newUser.setFirstName(user.getFirstName());
		return new ResponseEntity<>(this.repository.save(newUser), HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<User> delete(@PathVariable int id) {
		User user = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
		this.repository.delete(user);
		return ResponseEntity.ok(user);
	}

}
