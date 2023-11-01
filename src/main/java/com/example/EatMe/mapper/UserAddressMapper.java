package com.example.EatMe.mapper;

import com.example.EatMe.dto.UserAddressDTO;
import com.example.EatMe.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAddressMapper {
    public UserAddressDTO toUserAddress(User user){
        String name = user.getName();
        String surname = user.getSurname();
        String mail = user.getMail();
        String password = user.getPassword();
        return null;
    }
}
