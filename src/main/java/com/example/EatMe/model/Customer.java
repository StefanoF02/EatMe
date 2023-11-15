package com.example.EatMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends User {

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Order> madeOrders = new HashSet<>();

//    public Customer(int id, String UUID, String firstname, String surname, String mail, String password, Address address, Set<Order> madeOrders){
//        super(id,UUID,firstname,surname,mail,password,address);
//        this.madeOrders = madeOrders;
//
//    }

    public Set<Order> getMadeOrders() {
        return madeOrders;
    }

}
