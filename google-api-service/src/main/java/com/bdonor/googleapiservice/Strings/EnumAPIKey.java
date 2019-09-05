package com.bdonor.googleapiservice.Strings;

public enum EnumAPIKey {

    MAP_KEY(""),
    PLACES_KEY("");

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
