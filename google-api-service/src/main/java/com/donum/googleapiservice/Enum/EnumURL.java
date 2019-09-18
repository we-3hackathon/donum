package com.donum.googleapiservice.Enum;

public enum EnumURL {

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
    EnumURL(final String text) {
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
