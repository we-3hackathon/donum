package com.donum.accountservice.Enum;

public enum Template_Paths {


    RESET_PASSWORD("template-link"),
    EMAIL_CONFIRMATION("src/main/resources/Confirmation_Email_Template.ftl");

    private String text;

    Template_Paths(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
