package com.example.EatMe.address;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    Optional<Address> findAddressIdByUserId(int userId);
}
