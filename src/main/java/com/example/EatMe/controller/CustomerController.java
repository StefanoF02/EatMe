package com.example.EatMe.controller;

import com.example.EatMe.dto.CustomerAddressDTO;
import com.example.EatMe.model.Customer;
import com.example.EatMe.service.AddressService;
import com.example.EatMe.service.CustomerAddressService;
import com.example.EatMe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerAddressService customerAddressService;

    @PostMapping("/add")
    public ResponseEntity<CustomerAddressDTO> addCustomer(@RequestBody CustomerAddressDTO customerAddressDTO){
        CustomerAddressDTO customerToSave = customerAddressService.createUserAddressDTO(customerAddressDTO);
        if(customerToSave != null){
                return new ResponseEntity<CustomerAddressDTO>(customerToSave, HttpStatus.OK);
        }else{
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/edit/mail")
    public ResponseEntity<String> editMail(@RequestParam(value = "id") int id, @RequestParam(value = "mail") String mail){
        Customer customerToUpdate = customerService.setEmail(id, mail);
        if(customerToUpdate != null){
            return new ResponseEntity<String>("Email got updated to " + customerToUpdate.getMail(), HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Error user could not be found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/edit/password")
    public ResponseEntity<HttpStatus> editPassword(@RequestParam(value = "id") int id, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
        if (customerService.setPassword(id, oldPassword, newPassword) == HttpStatus.OK) {
            return new ResponseEntity("Password changed", HttpStatus.OK);
        } else if (customerService.setPassword(id, oldPassword, newPassword) == HttpStatus.UNAUTHORIZED) {
            return new ResponseEntity("Wrong password", HttpStatus.OK);
        } else {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CustomerAddressDTO> deleteUser(@RequestParam(value = "id") int id){
        if(customerAddressService.deleteUserAddressDTO(id) == true){
            return new ResponseEntity("User deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity("User not found", HttpStatus.OK);
        }
    }

}
