package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.models.UserList;
import com.github.javafaker.Faker;

@Service
public class UserListService {

	@Autowired
	private Faker faker;

	private List<UserList> users = new ArrayList<>();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			users.add(new UserList(faker.funnyName().name(), faker.name().username(), faker.dragonBall().character()));
		}
	}

//	public List<User> getUsers() {
//		return users;
//	}

	public List<UserList> getUsers(String startWith) {
		if (startWith != null) {
			return users.stream().filter(u -> u.getUsername().startsWith(startWith)).collect(Collectors.toList());
		} else {
			return users;
		}
	}

	public UserList getUserByUserName(String username) {
		return users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username)));
	}

	public UserList createUser(UserList user) {
		if (users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("User %s already exists", user.getUsername()));
		}
		users.add(user);
		return user;
	}

	public UserList updateUser(String username, UserList user) {
		UserList userToBeUpdated = getUserByUserName(username);
		userToBeUpdated.setNickName(user.getNickName());
		userToBeUpdated.setPassword(user.getPassword());
		return userToBeUpdated;
	}

	public void deleteUser(String username) {
		UserList userByUserName = getUserByUserName(username);
		users.remove(userByUserName);
	}

}
