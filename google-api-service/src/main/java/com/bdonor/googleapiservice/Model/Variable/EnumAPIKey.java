package com.bdonor.googleapiservice.Model.Variable;

public enum EnumAPIKey {

    MAP_KEY(""),
    PLACES_KEY("");

    private final String text;

    /**
     * @param text
     */
    EnumAPIKey(final String text) {
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
