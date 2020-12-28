package com.example.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.User;
import com.example.entities.UserPassword;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u.username FROM User u")
	Page<String> findAllUsername(Pageable pageable);
	
//	@Query("SELECT u.username, u.password FROM User u")
//	Page<Object> findAllUserAndPassword(Pageable pageable);
	
//	@Query("SELECT u.username, u.password FROM User u JOIN u.profile")
//	Page<Object> findAllUserAndPassword(Pageable pageable);
	
	Optional<User> findByUsername(String username);

}
