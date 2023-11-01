package com.example.EatMe.repository;

import com.example.EatMe.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByMail(String mail);

}
