package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entities.Address;
import com.example.entities.Profile;
import com.example.repositories.AddressRepository;
import com.example.repositories.ProfileRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ProfileRepository profileRepository;

	public List<Address> findAddressesByUserIdAndProfileId(Integer userId, Integer profileId) {
		return addressRepository.findByUserIdAndProfileId(userId, profileId);
	}

	public Address create(Integer userId, Integer profileId, Address address) {
		Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
		if (result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Not found userId %d and profileid %d", userId, profileId));
		}
	}

}
