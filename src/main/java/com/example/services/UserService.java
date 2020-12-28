package com.example.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entities.User;
import com.example.entities.UserPassword;
import com.example.repositories.UserRepository;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

//	public List<User> getUsers() {
//		return userRepository.findAll();
//	}

	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Page<String> getUserNames(Pageable pageable) {
		return userRepository.findAllUsername(pageable);
	}

//	public Page<Object> getUserAndPassword(Pageable pageable) {
//		return userRepository.findAllUserAndPassword(pageable);
//	}

	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId)));
	}

	@Cacheable("users")
	public User getUserByUsername(String username) {
		log.info("Getting user by username: {}", username);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return userRepository.findByUsername(username).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %S not found", username)));
	}
	
	@CacheEvict("users")	
	public void deleteByUsername(String username) {
		User user = getUserByUsername(username);
		userRepository.delete(user);
	}

}
