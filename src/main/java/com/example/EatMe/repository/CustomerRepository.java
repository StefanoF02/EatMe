package com.example.EatMe.repository;

import com.example.EatMe.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Optional<Customer> findByMail(String mail);

    Optional<Customer> findByUUID(String uuid);

}
