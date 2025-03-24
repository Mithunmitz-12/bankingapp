package com.bank.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.bankingapp.entity.User;
import com.bank.bankingapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; // For hashing passwords (e.g., BCrypt)
	

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(int userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
	}

//	public User getUserByUsername(String username) {
//		User user = userRepository.findByUsername(username);
//		if (user == null) {
//			throw new RuntimeException("User not found with username: " + username);
//		}
//		return user;
//	}

	public User createUser(User user) {
		// Hash the password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User updateUser(int userId, User userDetails) {
		User user = getUserById(userId);
		user.setUserName(userDetails.getUserName());
		if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		}
		user.setRole(userDetails.getRole());
		user.setEmail(userDetails.getEmail());
		return userRepository.save(user);
	}

	public void deleteUser(int userId) {
		User user = getUserById(userId);
		userRepository.delete(user);
	}

//	public User authenticate(String username, String password) {
//		User user = getUserByUsername(username);
//		if (passwordEncoder.matches(password, user.getPassword())) {
//			return user;
//		}
//		throw new RuntimeException("Invalid username or password");
//	}


}
