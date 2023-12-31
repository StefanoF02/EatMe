package com.example.EatMe.controller;

import com.example.EatMe.dto.VendorAddressDTO;
import com.example.EatMe.model.Vendor;
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
    @PostMapping("/add")
    public ResponseEntity<VendorAddressDTO> addVendor(@RequestBody VendorAddressDTO vendorAddressDTO){
        VendorAddressDTO vendorToSave =  vendorService.createVendorAddressDTO(vendorAddressDTO);
        if(vendorToSave != null){
            return new ResponseEntity<VendorAddressDTO>(vendorToSave, HttpStatus.OK);
        }else{
            return new ResponseEntity("Vendor already existing", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/edit/restaurantName")
    public ResponseEntity<Vendor> editName(@RequestParam(value= "uuid") String uuid,@RequestParam(value= "currentRestaurantName") String currentRestaurantName, @RequestParam(value= "newRestaurantName") String newRestaurantName){
        Vendor updatedVendor = vendorService.changeName(uuid,currentRestaurantName,newRestaurantName);
        if(updatedVendor != null){
            return new ResponseEntity("Name was changed to " + updatedVendor.getRestaurantName(), HttpStatus.OK);
        }else{
            return new ResponseEntity("Vendor not found", HttpStatus.BAD_REQUEST);
        }
    }
}
