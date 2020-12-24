package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

}
