package com.bdonor.uiservice.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/map")
public class MapController extends BaseController {

    @Override
    public void loadController() {
        _controllerName = "Map Controller";
    }

    @GetMapping("/static")
    public void generateStaticMap(HttpServletResponse http) {

        try {
            String output = "E";
            String staticMapUrl = "";

            URL url = new URL("http://localhost:8110/generatemap/London");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                staticMapUrl = output;
            }

            conn.disconnect();

            http.sendRedirect(staticMapUrl);
        } catch (
                MalformedURLException e) {

            e.printStackTrace();

        } catch (
                IOException e) {

            e.printStackTrace();

        }
    }
}
