package com.bdonor.googleapiservice.Model;

public class StaticMap implements Map {

    private String _zoom;
    private String _plots;
    private String _maptype;
    private String _city;
    private String _size = Variables.MEDIUM_RES.toString();


    @Override
    public void setZoom(String zoom) {
        _zoom = zoom;
    }

    @Override
    public void setSize(String size) {
        _size = size;
    }

    @Override
    public void setPlotSize(String plots) {
        _plots = plots;
    }

    @Override
    public void setCenter(String city) {
        _city = city;
    }

    @Override
    public void setMapType(String mapType) {
        _maptype = mapType;
    }

    @Override
    public String getZoom() {
        return "&zoom=" + _zoom;
    }

    @Override
    public String getSize() {
        return "&size=" + Variables.MEDIUM_RES.toString();
    }

    @Override
    public String getPlotSize() {
        return _plots;
    }

    @Override
    public String getCenter() {
        return "center=" + _city;
    }

    @Override
    public String getMapType() {
        return "&maptype="+ _maptype;
    }
}
