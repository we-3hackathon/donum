package com.Entity;

public class User {

    private String _name;
    private String _blood;
    private String _postcode;

    public User(String name, String blood, String postcode){
        _name = name;
        _blood = blood;
        _postcode = postcode;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getBlood() {
        return _blood;
    }

    public void setBlood(String _blood) {
        this._blood = _blood;
    }

    public String getPostcode() {
        return _postcode;
    }

    public void setPostcode(String _postcode) {
        this._postcode = _postcode;
    }
}
