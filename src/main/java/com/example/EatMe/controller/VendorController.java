package com.example.EatMe.controller;

import com.example.EatMe.dto.VendorAddressDTO;
import com.example.EatMe.model.Vendor;
import com.example.EatMe.service.VendorAddressService;
import com.example.EatMe.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @Autowired
    private VendorAddressService vendorAddressService;
    @PostMapping("/add")
    public ResponseEntity<VendorAddressDTO> addVendor(@RequestBody VendorAddressDTO vendorAddressDTO){
        VendorAddressDTO vendorToSave =  vendorAddressService.createVendorAddressDTO(vendorAddressDTO);
        if(vendorToSave != null){
            return new ResponseEntity<VendorAddressDTO>(vendorToSave, HttpStatus.OK);
        }else{
            return new ResponseEntity("Vendor already existing", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/edit/name")
    public ResponseEntity<Vendor> changeName(@RequestParam(value= "currentName") String currentName, @RequestParam(value= "newName") String newName){
        Vendor updatedVendor = vendorService.changeName(currentName,newName);
        if(updatedVendor != null){
            return new ResponseEntity("Name was changed to " + updatedVendor.getName(), HttpStatus.OK);
        }else{
            return new ResponseEntity("Vendor not found", HttpStatus.BAD_REQUEST);
        }
    }
}
