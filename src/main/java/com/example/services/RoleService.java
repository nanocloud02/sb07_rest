package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entities.Role;
import com.example.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> result = roleRepository.findById(roleId);
		if (result.isPresent()) {
			return roleRepository.save(role);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role Id: %d doesn't exist", roleId));
		}
	}

	public void deleteRole(Integer roleId) {
		Optional<Role> result = roleRepository.findById(roleId);
		if (result.isPresent()) {
			roleRepository.deleteById(roleId);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role Id: %d doesn't exist", roleId));
		}
	}

}
