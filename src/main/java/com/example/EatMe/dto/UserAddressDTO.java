package com.example.EatMe.dto;

import com.example.EatMe.model.Address;
import com.example.EatMe.model.User;

public class UserAddressDTO {

    private User user;
    private Address address;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
