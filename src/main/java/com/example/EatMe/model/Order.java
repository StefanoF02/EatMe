package com.example.EatMe.model;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
