package com.bdonor.googleapiservice.Model;


public abstract class User {

    private String _lat;
    private String _long;
    private String _bloodGroup;
    private String id;

    public User(String _lat, String _long, String _bloodGroup, String id) {
        this._lat = _lat;
        this._long = _long;
        this._bloodGroup = _bloodGroup;
        this.id = id;
    }

    public String get_lat() {
        return _lat;
    }

    public void set_lat(String _lat) {
        this._lat = _lat;
    }

    public String get_long() {
        return _long;
    }

    public void set_long(String _long) {
        this._long = _long;
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
}
