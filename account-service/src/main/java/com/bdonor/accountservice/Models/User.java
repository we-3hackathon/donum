package com.bdonor.accountservice.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AllUsers")
public class User {

    @Id
    private String id;
    private String bloodGroup;
    private String firstName; // DO NOT change this, needs to stay firstName
    @Indexed(direction = IndexDirection.ASCENDING)
    private String surname;
    private String email;
    private String password;
    private String addressline;
    private String postcode;
    private String latitude;
    private String longtitude;

    public User() {}

    public User(String bloodGroup, String firstname, String surname, String email, String encode, String addressline, String postcode, String latitude, String longtitude){}

    public User(String bloodGroup, String firstname, String surname, String email, String password, String addressline, String postcode) {
        this.bloodGroup = bloodGroup;
        this.firstName = firstname;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.addressline = addressline;
        this.postcode = postcode;
    }

    public User(String id, String bloodGroup, String firstname, String surname, String email, String password, String addressline, String postcode, String latitude, String longtitude) {
        this.id = id;
        this.bloodGroup = bloodGroup;
        this.firstName = firstname;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.addressline = addressline;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddressline() {
        return addressline;
    }

    public void setAddressline(String addressline) {
        this.addressline = addressline;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }


    @Override
    public String toString() {
        return "NewUser{" +
                "id='" + id + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", firstname='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", addressline='" + addressline + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
