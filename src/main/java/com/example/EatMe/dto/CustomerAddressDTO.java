package com.example.EatMe.dto;

import com.example.EatMe.model.Address;
import com.example.EatMe.model.Customer;

public class CustomerAddressDTO {

    private Customer customer;
    private Address address;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
