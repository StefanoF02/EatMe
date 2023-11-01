package com.example.EatMe.controller;

import com.example.EatMe.dto.UserAddressDTO;
import com.example.EatMe.model.Address;
import com.example.EatMe.service.AddressService;
import com.example.EatMe.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserAddressService userAddressService;
    @PostMapping("/add")
    public ResponseEntity<Address> addUser(@RequestBody Address newAddress){
        return addressService.addAddress(newAddress);

    }

    @GetMapping("/get")
    public ResponseEntity<Address> getAddress(@RequestParam(value = "userID") int id ){
        var userAddress = userAddressService.getAddress(id);
        if(userAddress != null){
            return new ResponseEntity<Address>(userAddress, HttpStatus.OK);
        }else{
            return new ResponseEntity("Error", HttpStatus.OK);
        }
    }

    @PatchMapping("/edit")
    public ResponseEntity<UserAddressDTO> editAddress(@RequestParam(value = "id") int id, @RequestBody Address newAddress){
        if(userAddressService.editAddress(id,newAddress) != null){
            return new ResponseEntity("Address edited", HttpStatus.OK);
        }else{
            return new ResponseEntity("Error", HttpStatus.OK);
        }

    }

}
