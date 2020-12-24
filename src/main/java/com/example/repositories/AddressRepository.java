package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
