package com.example.EatMe.controller;

import com.example.EatMe.model.Address;
import com.example.EatMe.model.User;
import com.example.EatMe.service.AddressService;
import com.example.EatMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody Address newAddress, @RequestBody User newUser){

        return userService.addUser(newUser);

    }

    @PatchMapping("/editMail")
    public ResponseEntity<User> editMail(@RequestParam(value = "id") int id,@RequestParam(value = "mail") String mail){
        return userService.setEmail(id, mail);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam(value = "id") int id){
        return userService.deleteUser(id);
    }

}
