package com.example.EatMe.service;

import com.example.EatMe.dto.VendorAddressDTO;
import com.example.EatMe.repository.AddressRepository;
import com.example.EatMe.repository.VendorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class VendorAddressService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private AddressRepository addressRepository;
    private ObjectMapper mapper;
    public VendorAddressDTO createVendorAddressDTO(@RequestBody VendorAddressDTO vendorAddressDTO) {
        var ven = vendorRepository.findByRestaurantName(vendorAddressDTO.getVendor().getRestaurantName());
        if(ven.isPresent()){
            return null;
        }else{
            var saveInDB = addressRepository.save(vendorAddressDTO.getAddress());
            vendorAddressDTO.getVendor().setAddress(saveInDB);
            vendorRepository.save(vendorAddressDTO.getVendor());
            return vendorAddressDTO;
        }
    }
}
