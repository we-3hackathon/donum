package com.donum.accountservice.Enum;

public enum UpdateCode {

    PASSWORD(1),
    ADDRESS(2),
    EMAIL(3),
    VERIFIED(4),
    TOKEN(5);

    private final int _code;

    /**
     * @param code
     */
    UpdateCode(int code) {
        this._code = code;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */

    public int value() {
        return _code;
    }
}
