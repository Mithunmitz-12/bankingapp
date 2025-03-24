package com.bank.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingapp.entity.LoginRequest;
import com.bank.bankingapp.entity.User;
import com.bank.bankingapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	// Get all users (admin-only in practice)
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
//
	// Login (authenticate user)
//	@PostMapping("/login")
//	public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
//		User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//		return new ResponseEntity<User>((user), HttpStatus.CREATED);
//	}

	// Update user details
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userDetails) {
		return new ResponseEntity<User>(userService.updateUser(userId, userDetails), HttpStatus.OK);
	}

	// Delete a user
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
