package com.bdonor.accountservice.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "AllUsers")
public class User {

    @Id
    private String _id;
    private String bloodGroup;
    private String firstName;
    @Indexed(direction = IndexDirection.ASCENDING)
    private String _surname;
    private String _email;
    private String _password;
    private String _addressline;
    private String _postcode;

    public User(){}

    public User(String _email, String _password) {
        this._email = _email;
        this._password = _password;
    }

    public User(String bloodGroup, String firstName, String _surname, String _email, String _password, String _addressline, String _postcode) {
        this.bloodGroup = bloodGroup;
        this.firstName = firstName;
        this._surname = _surname;
        this._email = _email;
        this._password = _password;
        this._addressline = _addressline;
        this._postcode = _postcode;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_addressline() {
        return _addressline;
    }

    public void set_addressline(String _addressline) {
        this._addressline = _addressline;
    }

    public String get_postcode() {
        return _postcode;
    }

    public void set_postcode(String _postcode) {
        this._postcode = _postcode;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", firstName='" + firstName + '\'' +
                ", _surname='" + _surname + '\'' +
                ", _email='" + _email + '\'' +
                ", _password='" + _password + '\'' +
                ", _addressline='" + _addressline + '\'' +
                ", _postcode='" + _postcode + '\'' +
                '}';
    }
}
