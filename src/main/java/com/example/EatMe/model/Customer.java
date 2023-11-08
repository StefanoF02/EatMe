package com.example.EatMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends User {

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Order> madeOrders = new HashSet<>();

    public Set<Order> getMadeOrders() {
        return madeOrders;
    }

}
