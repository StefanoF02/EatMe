package com.example.EatMe.model;

import jakarta.persistence.*;

import java.util.Set;

@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String orderKey;
//    private String timeStamp;
//    private String[] orderItems;
//    private int price;
//    private String payMethod;
//    private Vendor vendor;
    @ManyToMany(mappedBy = "orders")
    private Set<Customer> customers;

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
