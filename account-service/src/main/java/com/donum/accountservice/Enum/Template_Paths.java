package com.donum.accountservice.Enum;

import org.springframework.core.io.ClassPathResource;

public enum Template_Paths {


    RESET_PASSWORD("template-link"),
    EMAIL_CONFIRMATION("src/main/resources/Confirmation_Email_Template.html");

    private String text;

    Template_Paths(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
