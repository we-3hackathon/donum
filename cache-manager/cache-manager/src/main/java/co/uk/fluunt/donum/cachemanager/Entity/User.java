package co.uk.fluunt.donum.cachemanager.Entity;

public class User {

    private String _name;
    private String _blood;
    private String _postcode;
    private String _latitude;
    private String _longitude;

    public User(String name, String blood, String postcode, String lat, String lon){
        _name = name;
        _blood = blood;
        _postcode = postcode;
        _latitude = lat;
        _longitude = lon;
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
