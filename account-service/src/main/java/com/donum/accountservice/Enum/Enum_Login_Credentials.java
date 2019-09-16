package com.donum.accountservice.Enum;

public enum Enum_Login_Credentials {

    GMAIL_PASSWORD("");

    private String text;

    Enum_Login_Credentials(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
