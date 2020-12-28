package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entities.Profile;
import com.example.entities.User;
import com.example.repositories.ProfileRepository;
import com.example.repositories.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	public Profile create(Integer userId, Profile profile) {
		Optional<User> result = userRepository.findById(userId);
		if (result.isPresent()) {
			profile.setUser(result.get());
			return profileRepository.save(profile);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
	}

}
