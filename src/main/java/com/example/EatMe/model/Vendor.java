package com.example.EatMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Vendor extends User{

    @Column(nullable = false)
    private String restaurantName;

    @JsonIgnore
    @OneToMany(mappedBy = "vendor")
    private Set<Order> receivedOrders = new HashSet<>();

//    public Vendor(int id, String UUID, String firstname, String surname, String mail, String password, Address address, Set<Order> receivedOrders){
//        super(id, UUID, firstname,surname,mail,password,address);
//        this.receivedOrders = receivedOrders;
//    }
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
