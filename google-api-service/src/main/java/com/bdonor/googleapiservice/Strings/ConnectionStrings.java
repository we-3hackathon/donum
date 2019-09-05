package com.bdonor.googleapiservice.Strings;

public enum ConnectionStrings {

    APIKEY_SERVER("http://18.130.137.35:5333/get-key/"),
    ACCOUNT_SERVICE("http://40.121.148.131:8000/account-service/");


    private String text;

    /**
     * @param text
     */
    ConnectionStrings(String text) {
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
