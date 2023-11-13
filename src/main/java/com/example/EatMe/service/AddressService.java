package com.example.EatMe.service;

import com.example.EatMe.model.Address;
import com.example.EatMe.repository.AddressRepository;
import com.example.EatMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<Address> addAddress(Address address){
            addressRepository.save(address);
            return new ResponseEntity("New address created.", HttpStatus.OK);
        }
    public Address editAddress(int id, Address updatedAddress){
        var customerInDB = customerRepository.findById(id);
        if(customerInDB.isPresent()){
            var addressId = customerInDB.get().getAddress().getId();
            addressRepository.save(updatedAddress);
            customerInDB.get().setAddress(updatedAddress);
            addressRepository.deleteById(addressId);
            return updatedAddress;
        }
        return null;
    }

    public Address getAddress(int id){
        var customerInDB = customerRepository.findById(id);
        if(customerInDB.isPresent()){
            Address returningAddress = customerInDB.get().getAddress();
            return returningAddress;
        }else{
            return null;
        }
    }

}
