package com.donum.googleapiservice.Enum;

public enum EnumGoogleMap {

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
    EnumGoogleMap(final String text) {
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
