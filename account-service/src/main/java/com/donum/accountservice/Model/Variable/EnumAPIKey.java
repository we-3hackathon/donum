package com.donum.accountservice.Model.Variable;

public enum EnumAPIKey {

    DYNAMO_KEY(""),
    DYNAMO_SECRET_KEY("");

    private String text;

    /**
     * @param text
     */
    EnumAPIKey(String text) {
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
