package com.example.EatMe.service;

import com.example.EatMe.model.Vendor;
import com.example.EatMe.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public Vendor changeName(@RequestParam(value = "currentName") String currentName, @RequestParam(value = "newName") String newName){
        Optional<Vendor> vendorInDB = vendorRepository.findByName(currentName);
        if(vendorInDB.isPresent()){
            //Comparing old Name to new Name
            vendorInDB.get().setName(newName);
            Vendor vendorSaved = vendorRepository.save(vendorInDB.get());
            return vendorSaved;
        }else{
            return null;
        }

    }
}
