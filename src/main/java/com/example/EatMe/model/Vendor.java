package com.example.EatMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Vendor extends User{

    @Column(nullable = false)
    private String restaurantName;

    @JsonIgnore
    @OneToMany(mappedBy = "vendor")
    private Set<Order> receivedOrders = new HashSet<>();

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
