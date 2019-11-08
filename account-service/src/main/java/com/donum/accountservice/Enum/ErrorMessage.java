package com.donum.accountservice.Enum;

public enum ErrorMessage {

    EMPTY("Collection empty"),
    SUCCESS("Success"),
    FAIL("Fail"),
    DYNAMO_OFFLINE("Aroundhackathon@gmail.com"),
    DYNAMO_ONLINE("Reset Password"),
    USER_NOTFOUND("User does not exist"),
    EMAIL_INUSE("Email already in use, please try another one");

    private String text;

    /**
     * @param text
     */
    ErrorMessage(String text) {
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
