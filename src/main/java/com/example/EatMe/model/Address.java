package com.example.EatMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private int streetNumber;
    @Column(nullable = false)
    private int postcode;
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Customer customer;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Vendor vendor;

    public Address(int id, String street, int streetNumber, int postcode) {
        this.id = id;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postcode = postcode;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
