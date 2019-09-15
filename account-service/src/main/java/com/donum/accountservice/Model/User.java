package com.donum.accountservice.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.io.Serializable;

@DynamoDBTable(tableName = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String accesscode;
    private String bloodGroup;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String addressline;
    private String postcode;
    private String latitude;
    private String longitude;
    private boolean isVerified;

    public User() {}

    public User(String bloodGroup, String firstName, String lastName, String email, String password, String addressline, String postcode, String latitude, String longitude) { // Proper Constructor
        this.bloodGroup = bloodGroup;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.addressline = addressline;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public User(String accesscode, String bloodGroup, String firstName, String lastName, String email, String password, String addressline, String postcode, String latitude, String longitude, boolean isVerified) {
        this.accesscode = accesscode;
        this.bloodGroup = bloodGroup;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.addressline = addressline;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isVerified = isVerified;
    }

    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccesscode() {
        return accesscode;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    @DynamoDBAttribute
    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @DynamoDBHashKey(attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBRangeKey
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute
    public String getAddressline() {
        return addressline;
    }

    public void setAddressline(String addressline) {
        this.addressline = addressline;
    }

    @DynamoDBAttribute
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @DynamoDBAttribute
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @DynamoDBAttribute
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", addressline='" + addressline + '\'' +
                ", postcode='" + postcode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }


}
