package com.donum.accountservice.Enum;

public enum EmailFields {

    FROM("Aroundhackathon@gmail.com"),
    RESET_SUBJECT("Reset Password"),
    CONFIRMATION_SUBJECT("Email Confirmation");

    private String text;

    /**
     * @param text
     */
    EmailFields(String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
