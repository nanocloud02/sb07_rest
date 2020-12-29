package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

	@Query("SELECT a FROM Address a WHERE a.profile.user.id = ?1 AND a.profile.id = ?2")
	List<Address> findByUserIdAndProfileId(Integer userId, Integer profileId);

}
