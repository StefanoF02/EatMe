package com.example.EatMe.controller;

import com.example.EatMe.dto.VendorAddressDTO;
import com.example.EatMe.service.VendorAddressService;
import com.example.EatMe.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
