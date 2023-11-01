package com.example.EatMe.dto;

import com.example.EatMe.model.Address;
import com.example.EatMe.model.Vendor;

public class VendorAddressDTO {

    private Vendor vendor;

    private Address address;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
