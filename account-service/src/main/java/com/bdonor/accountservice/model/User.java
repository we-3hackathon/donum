package com.bdonor.accountservice.model;

import org.springframework.data.annotation.Id;
import javax.persistence.Entity;

@Entity
public class User {

    @Id
    String firstName;
    String surname;
    char Email;
    char Password;
    char nhsID;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getEmail() {
        return Email;
    }

    public void setEmail(char email) {
        Email = email;
    }

    public char getPassword() {
        return Password;
    }

    public void setPassword(char password) {
        Password = password;
    }

    public char getNhsID() {
        return nhsID;
    }

    public void setNhsID(char nhsID) {
        this.nhsID = nhsID;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", Email=" + Email +
                ", Password=" + Password +
                ", nhsID=" + nhsID +
                '}';
    }


}
