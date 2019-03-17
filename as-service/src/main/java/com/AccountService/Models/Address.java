package com.AccountService.Models;

public class Address {

    private String _addressline;
    private String _postcode;
    private String _county;

    public Address(String _addressline, String _postcode, String _county) {
        this._addressline = _addressline;
        this._postcode = _postcode;
        this._county = _county;
    }

    public String get_addressline() {
        return _addressline;
    }

    public void set_addressline(String _addressline) {
        this._addressline = _addressline;
    }

    public String get_postcode() {
        return _postcode;
    }

    public void set_postcode(String _postcode) {
        this._postcode = _postcode;
    }

    public String get_county() {
        return _county;
    }

    public void set_county(String _county) {
        this._county = _county;
    }


}
