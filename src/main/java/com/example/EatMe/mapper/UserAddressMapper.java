package com.example.EatMe.mapper;

import com.example.EatMe.dto.CustomerAddressDTO;
import com.example.EatMe.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAddressMapper {
    public CustomerAddressDTO toUserAddress(User user){
        String name = user.getFirstname();
        String surname = user.getSurname();
        String mail = user.getMail();
        String password = user.getPassword();
        return null;
    }
}
