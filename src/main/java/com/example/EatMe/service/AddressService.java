package com.example.EatMe.service;

import com.example.EatMe.model.Address;
import com.example.EatMe.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity<Address> addAddress(@RequestBody Address address){
            addressRepository.save(address);
            return new ResponseEntity("New address created.", HttpStatus.OK);
        }

}
