package com.example.EatMe.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity<Address> addAddress(@RequestBody Address address){
            addressRepository.save(address);
            return new ResponseEntity("New address created.", HttpStatus.OK);
        }

    public ResponseEntity<Address> findAddressByUserId(@RequestParam int id){
        var addressInDB = addressRepository.findAddressIdByUserId(id);
        return new ResponseEntity<Address>(addressInDB.get(), HttpStatus.OK);
    }
    public ResponseEntity<Address> deleteAddress(@RequestParam int id){
        var addressInDB = addressRepository.findAddressIdByUserId(id);
        addressRepository.delete(addressInDB.get());
        return new ResponseEntity("Address deleted", HttpStatus.OK);
    }

}
