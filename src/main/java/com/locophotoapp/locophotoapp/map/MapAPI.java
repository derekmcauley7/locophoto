package com.locophotoapp.locophotoapp.map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class MapAPI {

    @Value(value = "${google_api_key:testingDefault}")
    String API_KEY;

    public String getGeoResults(String lat, String lng) throws IOException {
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "2&key=" + API_KEY);
        URLConnection conn = url.openConnection();
        conn.connect();
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
        StringBuffer sbLocation = new StringBuffer();

        for (int i=0; i != -1; i = isr.read())
            sbLocation.append((char) i);

        String getContent = sbLocation.toString().trim();
        return getContent;
    }

}
