package com.example.EatMe.user;

import com.example.EatMe.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<UserAddressCreationDTO> createDTO(@RequestBody UserAddressCreationDTO userAddressCreationDTO){
        var newUserInDB = userService.addUser(userAddressCreationDTO.getUser());
        userAddressCreationDTO.getAddress().setUserId(newUserInDB.getBody().getId());
        addressService.addAddress(userAddressCreationDTO.getAddress());
        return new ResponseEntity<UserAddressCreationDTO>(userAddressCreationDTO, HttpStatus.OK);
    }

    @PatchMapping("/editMail")
    public ResponseEntity<User> editMail(@RequestParam(value = "id") int id,@RequestParam(value = "mail") String mail){
        return userService.setEmail(id, mail);
    }

    @PatchMapping("/editPassword")
    public ResponseEntity<User> editPassword(@RequestParam(value = "id") int id, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword){
        return userService.setPassword(id,oldPassword,newPassword);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam(value = "id") int id){
        addressService.deleteAddress(id);
        return userService.deleteUser(id);
    }

}
