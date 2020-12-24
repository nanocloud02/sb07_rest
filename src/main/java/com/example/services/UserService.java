package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.models.User;
import com.example.repositories.UserRepository;
import com.github.javafaker.Faker;

@Service
public class UserService {

	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository userRepository;

	private List<User> users = new ArrayList<>();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			users.add(new User(faker.funnyName().name(), faker.name().username(), faker.dragonBall().character()));
		}
	}

//	public List<User> getUsers() {
//		return users;
//	}

	public List<User> getUsers(String startWith) {
		if (startWith != null) {
			return users.stream().filter(u -> u.getUsername().startsWith(startWith)).collect(Collectors.toList());
		} else {
			return users;
		}
	}
	
	public List<com.example.entities.User> getUsersDB() {
		return userRepository.findAll();
	}

	public User getUserByUserName(String username) {
		return users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username)));
	}

	public User createUser(User user) {
		if (users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("User %s already exists", user.getUsername()));
		}
		users.add(user);
		return user;
	}

	public User updateUser(String username, User user) {
		User userToBeUpdated = getUserByUserName(username);
		userToBeUpdated.setNickName(user.getNickName());
		userToBeUpdated.setPassword(user.getPassword());
		return userToBeUpdated;
	}

	public void deleteUser(String username) {
		User userByUserName = getUserByUserName(username);
		users.remove(userByUserName);
	}

}
