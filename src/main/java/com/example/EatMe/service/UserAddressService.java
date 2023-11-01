package com.example.EatMe.service;

import com.example.EatMe.dto.UserAddressDTO;
import com.example.EatMe.model.Address;
import com.example.EatMe.repository.AddressRepository;
import com.example.EatMe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserAddressService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    public UserAddressDTO createUserAddressDTO(@RequestBody UserAddressDTO userAddressDTO){
        var userInDB = userRepository.findByMail(userAddressDTO.getUser().getMail());
        if(userInDB.isPresent()){
            return null;
        }else{
            var saveInDB = addressRepository.save(userAddressDTO.getAddress());
            userAddressDTO.getUser().setAddress(saveInDB);
            userRepository.save(userAddressDTO.getUser());
            return userAddressDTO;
        }
    }

    public Address editAddress(@RequestParam(value = "id") int id,@RequestBody Address updatedAddress){
        var userInDB = userRepository.findById(id);
        if(userInDB.isPresent()){
            var addressId = userInDB.get().getAddress().getId();
            addressRepository.save(updatedAddress);
            userInDB.get().setAddress(updatedAddress);
            addressRepository.deleteById(addressId);
            return updatedAddress;
        }
        return null;
    }

    public Address getAddress(@RequestParam(value = "id") int id){
        var userInDB = userRepository.findById(id);
        if(userInDB.isPresent()){
        Address returningAddress =userInDB.get().getAddress();
        return returningAddress;
        }else{
            return null;
        }
    }

    public boolean deleteUserAddressDTO(@RequestParam(value = "id") int id){
        var userToDelete = userRepository.findById(id);
        if(userToDelete.isPresent()){
            var addressIDToDelete = userToDelete.get().getAddress().getId();
            addressRepository.deleteById(addressIDToDelete);
            userRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
