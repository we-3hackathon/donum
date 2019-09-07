package com.bdonor.accountservice.Links;

public enum EnumAPI_Links {

    GOOGLE_API("http://localhost:8000/google-api-service/"),
    POSTCODE_API("https://api.postcodes.io/");

    private String txt;

    EnumAPI_Links(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return txt;
    }
}
