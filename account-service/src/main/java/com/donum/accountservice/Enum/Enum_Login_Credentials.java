package com.donum.accountservice.Enum;

public enum Enum_Login_Credentials {

    GMAIL_PASSWORD("");

    private String text;

    /**
     * @param text
     */
    Enum_Login_Credentials(String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

    public void updateKeyWith(String text) {
        this.text = text;
    }
}
