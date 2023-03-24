package com.EasyBid.UserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userID;
    private String password;
    private String name;
    private LocalDate dob;
    private String phoneNumber;
    private String address;
    private String paymentOptionID;

    public User() {    }
    public User(String userID, String password, String name, LocalDate dob, String phoneNumber, String address, String paymentOptionID) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.paymentOptionID = paymentOptionID;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentOptionID() {
        return paymentOptionID;
    }

    public void setPaymentOptionID(String paymentOptionID) {
        this.paymentOptionID = paymentOptionID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", paymentOptionID='" + paymentOptionID + '\'' +
                '}';
    }
}
