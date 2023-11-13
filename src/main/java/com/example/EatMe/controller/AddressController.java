package com.example.EatMe.controller;

import com.example.EatMe.dto.CustomerAddressDTO;
import com.example.EatMe.model.Address;
import com.example.EatMe.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;


    @GetMapping("/get")
    public ResponseEntity<Address> getAddress(@RequestParam(value = "userID") int id ){
        var userAddress = addressService.getAddress(id);
        if(userAddress != null){
            return new ResponseEntity<Address>(userAddress, HttpStatus.OK);
        }else{
            return new ResponseEntity("Error", HttpStatus.OK);
        }
    }

    @PatchMapping("/edit")
    public ResponseEntity<CustomerAddressDTO> editAddress(@RequestParam(value = "id") int id, @RequestBody Address newAddress){
        if(addressService.editAddress(id,newAddress) != null){
            return new ResponseEntity("Address edited", HttpStatus.OK);
        }else{
            return new ResponseEntity("Error", HttpStatus.OK);
        }

    }

}
