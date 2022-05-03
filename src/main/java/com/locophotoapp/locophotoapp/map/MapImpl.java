package com.locophotoapp.locophotoapp.map;

import ch.qos.logback.classic.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class MapImpl implements Map {

    @Value(value = "${google_api_key:testingDefault}")
    String API_KEY;

    private Logger logger = (Logger) LoggerFactory.getLogger(MapImpl.class);

    @Override
    public String getCity(String lat, String lng) {
        String cityName = null;
        try
        {
            String getContent = getGeoResults(lat, lng);
            if(getContent.contains("results")) {
                String temp = getContent.substring(getContent.indexOf("["));
                JSONArray JSONArrayForAll = new JSONArray(temp);
                JSONObject addressObject = JSONArrayForAll.getJSONObject(0).getJSONObject("address_components").getJSONObject("long_name").getJSONObject("types");
                String possibleCity1 = addressObject.get("administrative_area_level_1").toString();
                String possibleCity2 = addressObject.get("locality").toString();
                String possibleCity3 = addressObject.get("postal_town").toString();
                String possibleCity4 = addressObject.get("country").toString();
                cityName = possibleCity1;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cityName;
    }

    private String getGeoResults(String lat, String lng) throws IOException {
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
