package com.bdonor.googleapiservice.Model;

public interface Map {

    void setZoom(String zoom);

    void setSize(String size);

    void setPlotSize(String plots);

    void setCenter(String city);

    void setMapType(String mapType);

    String getZoom();

    String getSize();

    String getPlotSize();

    String getCenter();

    String getMapType();

}
