package com.bdonor.googleapiservice.Model;

public enum GoogleKey {


    API_KEY("yourkeyhere"),
    ;

    private final String text;

    /**
     * @param text
     */
    GoogleKey(final String text) {
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
