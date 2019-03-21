package com.bdonor.googleapiservice.Model;


public abstract class User {

    private String _postcode;
    private String _address;
    private String _bloodGroup;
    private String id;


    public User( String _postcode, String _address, String _bloodGroup) {
        this._postcode = _postcode;
        this._address = _address;
        this._bloodGroup = _bloodGroup;
    }

    public String get_bloodGroup() {
        return _bloodGroup;
    }

    public void set_bloodGroup(String _bloodGroup) {
        this._bloodGroup = _bloodGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_postcode() {
        return _postcode;
    }

    public void set_postcode(String _postcode) {
        this._postcode = _postcode;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
}
