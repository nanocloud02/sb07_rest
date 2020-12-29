package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.UserInRole;

@Repository
public interface UserInRoleRepository extends CrudRepository<UserInRole, Integer> {

}
