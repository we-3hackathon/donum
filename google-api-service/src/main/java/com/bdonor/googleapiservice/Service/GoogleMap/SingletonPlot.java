package com.bdonor.googleapiservice.Service.GoogleMap;

import com.bdonor.googleapiservice.Model.Variable.EnumGoogleMap;
import com.bdonor.googleapiservice.Service.Json.Process;

public class SingletonPlot {

    private static SingletonPlot plot = new SingletonPlot();

    /**
     * Singleton for a more efficient resource usage and sharing
     * */
    public static SingletonPlot getInstance() {

        if (null == plot) {
            plot = new SingletonPlot();
            System.out.println("Instance created: Service/SingletonPlot");
        }
        return plot;
    }

    private SingletonPlot() {_plotURL = new StringBuilder();}

    private StringBuilder _plotURL ;

    public void addMarker(String _markerColor, String _label, String _cordX, String _cordY){

        String _markerURL = EnumGoogleMap.MARKER.toString() + _markerColor + "|" + "label:"+ _label +"|"+ _cordX + "," + _cordY;

        _plotURL.append(_markerURL);
        System.out.println(_plotURL.toString());
    }

    /**
     * Color will depend on blood group type
     * Red for negative (-)
     * Green for positive (+)
     * ex. O+ will be green whereas O- will be red
     * Issues: AB bloodgroup
     * */
    public String setColour(String bloodGroup){

        if(bloodGroup.contains("+")){
            return "green";
        }else if(bloodGroup.contains("-")){
            return "red";
        }else{
            return "grey";
        }

    }

    public String getPlotURL(){return _plotURL.toString();}

    /**
     * Handle complexity to Json Service
     * @param JSONData - the data from account-service
     */
    public String processPlot(String JSONData){

        Process ProcessThread = new Process(JSONData);

        ProcessThread.setState(1);
        ProcessThread.run();

        while(ProcessThread.isAlive()){}


        return ProcessThread.getOutcome();

    }
}
