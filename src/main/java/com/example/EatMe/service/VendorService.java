package com.example.EatMe.service;

import com.example.EatMe.model.Vendor;
import com.example.EatMe.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public Vendor changeName(String currentRestaurantName, String newRestaurantName){
        Optional<Vendor> vendorInDB = vendorRepository.findByRestaurantName(currentRestaurantName);
        if(vendorInDB.isPresent()){
            //Comparing old Name to new Name
            vendorInDB.get().setRestaurantName(newRestaurantName);
            Vendor vendorSaved = vendorRepository.save(vendorInDB.get());
            return vendorSaved;
        }else{
            return null;
        }

    }
}
