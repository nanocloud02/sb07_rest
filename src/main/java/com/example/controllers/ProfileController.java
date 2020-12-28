package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Profile;
import com.example.services.ProfileService;

@Controller
@RequestMapping("/users/{userId}/profiles")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@PostMapping
	public ResponseEntity<Profile> create(@PathVariable("userId") Integer userId, @RequestBody Profile profile) {
		return new ResponseEntity<Profile>(profileService.create(userId, profile), HttpStatus.CREATED);
	}

}
