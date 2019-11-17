package com.donum.accountservice.Enum;

public enum ErrorMessage {

    EMPTY("Collection empty"),
    SUCCESS("Success"),
    FAIL("Fail"),
    DYNAMO_OFFLINE("Dynamo connection: Offline"),
    DYNAMO_ONLINE(("Dynamo connection: Online"),
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
