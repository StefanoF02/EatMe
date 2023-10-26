package com.example.Fooderando;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        userRepository.save(newUser);
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

}
