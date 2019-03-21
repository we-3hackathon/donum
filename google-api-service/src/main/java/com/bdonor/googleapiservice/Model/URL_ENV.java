package com.bdonor.googleapiservice.Model;

public enum URL_ENV {

    PROTOCOL("https://"),
    HOST("maps.googleapis.com"),
    PATH("/maps/api/geocode/json?"),
    ADDRESS("&address="),
    KEY("&key=")
    ;

    private final String text;

    /**
     * @param text
     */
    URL_ENV(final String text) {
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
