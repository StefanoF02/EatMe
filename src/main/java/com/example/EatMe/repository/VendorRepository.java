package com.example.EatMe.repository;

import com.example.EatMe.model.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface VendorRepository extends CrudRepository<Vendor, Integer> {
    Optional<Vendor> findByRestaurantName(String restaurantName);
}
