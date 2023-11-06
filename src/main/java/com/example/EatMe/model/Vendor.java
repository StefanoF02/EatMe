package com.example.EatMe.model;

import jakarta.persistence.*;

@Entity
public class Vendor extends User{

    @Column(nullable = false)
    private String restaurantName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
