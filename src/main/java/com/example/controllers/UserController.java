package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.User;
import com.example.services.UserService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@Timed(value = "get.users")
	public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
		return new ResponseEntity<>(userService.getUsers(pageable), HttpStatus.OK);
	}

//	@GetMapping("/username")
//	@ApiOperation(value = "View a list of usersnames", response = Page.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 400, message = "The records was not found"),
//	})
//	public ResponseEntity<Page<String>> getUsername(Pageable pageable) {
//		return new ResponseEntity<>(userService.getUserNames(pageable), HttpStatus.OK);
//	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUsername(@PathVariable("username") String username) {
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@DeleteMapping("/username/{username}")
	public ResponseEntity<Void> deleteUserByUsername(@PathVariable("username") String username) {
		userService.deleteByUsername(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

//	@GetMapping("/userAndPassword")
//	public ResponseEntity<Page<Object>> getUserAndPassword(Pageable pageable) {
//		return new ResponseEntity<>(userService.getUserAndPassword(pageable), HttpStatus.OK);
//	}

}
