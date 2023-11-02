package com.example.EatMe.controller;

import com.example.EatMe.dto.UserAddressDTO;
import com.example.EatMe.model.User;
import com.example.EatMe.service.AddressService;
import com.example.EatMe.service.UserAddressService;
import com.example.EatMe.service.UserService;
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

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("/add")
    public ResponseEntity<UserAddressDTO> addUser(@RequestBody UserAddressDTO userAddressDTO){
        UserAddressDTO userToSave = userAddressService.createUserAddressDTO(userAddressDTO);
        if(userToSave != null){
                return new ResponseEntity<UserAddressDTO>(userToSave, HttpStatus.OK);
        }else{
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/edit/mail")
    public ResponseEntity<String> editMail(@RequestParam(value = "id") int id, @RequestParam(value = "mail") String mail){
        User updatedUser = userService.setEmail(id, mail);
        if(updatedUser != null){
            return new ResponseEntity<String>("Email got updated to " + updatedUser.getMail(), HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Error user could not be found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/edit/password")
    public ResponseEntity<HttpStatus> editPassword(@RequestParam(value = "id") int id, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
        if (userService.setPassword(id, oldPassword, newPassword) == HttpStatus.OK) {
            return new ResponseEntity("Password changed", HttpStatus.OK);
        } else if (userService.setPassword(id, oldPassword, newPassword) == HttpStatus.UNAUTHORIZED) {
            return new ResponseEntity("Wrong password", HttpStatus.OK);
        } else {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserAddressDTO> deleteUser(@RequestParam(value = "id") int id){
        if(userAddressService.deleteUserAddressDTO(id) == true){
            return new ResponseEntity("User deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity("User not found", HttpStatus.OK);
        }
    }

}
