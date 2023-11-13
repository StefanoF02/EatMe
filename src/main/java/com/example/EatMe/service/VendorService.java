package com.example.EatMe.service;

import com.example.EatMe.dto.VendorAddressDTO;
import com.example.EatMe.model.Vendor;
import com.example.EatMe.repository.AddressRepository;
import com.example.EatMe.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private AddressRepository addressRepository;

    public VendorAddressDTO createVendorAddressDTO(VendorAddressDTO vendorAddressDTO) {
        var ven = vendorRepository.findByRestaurantName(vendorAddressDTO.getVendor().getRestaurantName());
        if (ven.isPresent()) {
            return null;
        } else {
            var saveInDB = addressRepository.save(vendorAddressDTO.getAddress());
            vendorAddressDTO.getVendor().setAddress(saveInDB);
            vendorAddressDTO.getVendor().setUuid(UUID.randomUUID().toString());
            vendorRepository.save(vendorAddressDTO.getVendor());
            return vendorAddressDTO;
        }
    }

    public Vendor changeName(String uuid, String currentRestaurantName, String newRestaurantName) {
        Optional<Vendor> vendorInDB = vendorRepository.findByUUID(uuid);
        if (vendorInDB.isPresent() && currentRestaurantName != newRestaurantName) {
            //Comparing old Name to new Name
            vendorInDB.get().setRestaurantName(newRestaurantName);
            Vendor vendorSaved = vendorRepository.save(vendorInDB.get());
            return vendorSaved;
        } else {
            return null;
        }

    }
}

