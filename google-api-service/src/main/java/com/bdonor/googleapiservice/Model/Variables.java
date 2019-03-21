package com.bdonor.googleapiservice.Model;

public enum Variables {


    API_KEY("AIzaSyBz-en4IzG0aeAxcGWc3Xo0fURt-Fb2-sU"),
    PLACES_KEY("AIzaSyCKMh-4IFwLRnoQSDQOuvhwj0QM7rGm8m8"),
    HIGH_RES("900x600"),
    MEDIUM_RES("600x480"),
    LOW_RES("480x240"),
    ROADMAP("roadmap"),
    STATIC_MAP("https://maps.googleapis.com/maps/api/staticmap?"),
    MARKER("&markers=color:")
    ;

    private final String text;

    /**
     * @param text
     */
    Variables(final String text) {
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
