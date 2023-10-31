package com.example.EatMe.user;

import com.example.EatMe.address.Address;

public class UserAddressCreationDTO {

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
