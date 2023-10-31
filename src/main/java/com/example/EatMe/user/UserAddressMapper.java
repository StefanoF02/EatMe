package com.example.EatMe.user;

import org.springframework.stereotype.Component;

@Component
public class UserAddressMapper {
    public UserAddressCreationDTO toUserAddress(User user){
        String name = user.getName();
        String surname = user.getSurname();
        String mail = user.getMail();
        String password = user.getPassword();
        return null;
    }
}
