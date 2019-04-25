package com.bdonor.googleapiservice.Model.Variable;

public enum EnumAPIKey {

    MAP_KEY("AIzaSyBz-en4IzG0aeAxcGWc3Xo0fURt-Fb2-sU"),
    PLACES_KEY("AIzaSyCKMh-4IFwLRnoQSDQOuvhwj0QM7rGm8m8");

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
