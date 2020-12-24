package com.example.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

//	@GetMapping
//	public ResponseEntity<List<User>> getUsers() {
//		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
//	}
	
	@GetMapping
	//public ResponseEntity<List<User>> getUsers(@RequestParam("startWith") String startWith) {
	public ResponseEntity<List<User>> getUsers(@RequestParam(value = "startWith", required = false) String startWith) {
		return new ResponseEntity<List<User>>(userService.getUsers(startWith), HttpStatus.OK);
	}
	
	@GetMapping("/db")
	public ResponseEntity<List<com.example.entities.User>> getUsersDb() {
		return new ResponseEntity<List<com.example.entities.User>>(userService.getUsersDB(), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{username}")
	public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(username, user), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
