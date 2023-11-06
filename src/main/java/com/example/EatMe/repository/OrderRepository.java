package com.example.EatMe.repository;

import com.example.EatMe.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    Optional<Order> findById(int id);
}
