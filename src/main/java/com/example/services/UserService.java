package com.example.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.models.User;
import com.github.javafaker.Faker;

@Service
public class UserService {

	@Autowired
	private Faker faker;

	private List<User> users = new ArrayList<>();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			users.add(new User(faker.funnyName().name(), faker.name().username(), faker.dragonBall().character()));
		}
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUserByUserName(String username) {
		return users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username)));
	}

}
