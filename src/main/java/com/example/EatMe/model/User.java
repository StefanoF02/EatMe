package com.example.EatMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String UUID;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address address;

    @Column(nullable = true)
    private String role;

//    public User(int id,String UUID, String firstname, String surname, String mail, String password, Address address) {
//        this.id = id;
//        this.UUID = UUID;
//        this.firstname = firstname;
//        this.surname = surname;
//        this.mail = mail;
//        this.password = password;
//        this.address = address;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return UUID;
    }

    public void setUuid(String uuid) {
        this.UUID = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


