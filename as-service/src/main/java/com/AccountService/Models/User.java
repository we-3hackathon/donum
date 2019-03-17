package com.AccountService.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class User {

    @Id
    private String _firstname;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String _surname;
    private String _email;
    private int _nhsid;
    private String _password;
    private Address _address;


    public User(String _firstname, String _surname, String _email, int _nhsid, String _password, Address _address) {
        this._firstname = _firstname;
        this._surname = _surname;
        this._email = _email;
        this._nhsid = _nhsid;
        this._password = _password;
        this._address = _address;
    }

    public String get_firstname() {
        return _firstname;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
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

    public int get_nhsid() {
        return _nhsid;
    }

    public void set_nhsid(int _nhsid) {
        this._nhsid = _nhsid;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public Address get_address() {
        return _address;
    }

    public void set_address(Address _address) {
        this._address = _address;
    }

    @Override
    public String toString() {
        return "User{" +
                "_firstname='" + _firstname + '\'' +
                ", _surname='" + _surname + '\'' +
                ", _email='" + _email + '\'' +
                ", _nhsid=" + _nhsid +
                ", _password='" + _password + '\'' +
                ", _address=" + _address +
                '}';
    }
}
