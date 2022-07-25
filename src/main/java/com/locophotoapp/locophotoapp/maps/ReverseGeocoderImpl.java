package com.locophotoapp.locophotoapp.maps;

import org.apache.http.util.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;

@Component
class ReverseGeocoderImpl implements ReverseGeocoder {

    @Resource
    MapAPI mapAPI;

    private ReverseGeocoderImpl(MapAPI mapAPI) {
        this.mapAPI = mapAPI;
    }

    @Override
    public String getCity(String lat, String lng) {
        try {
            String getContent = getGeoResults(lat, lng);
            if (getContent.contains("results")) {
                String temp = getContent.substring(getContent.indexOf("["));
                JSONArray JSONArrayForAll = new JSONArray(temp);

                JSONArray addressComponents = JSONArrayForAll.getJSONObject(0).getJSONArray("address_components");

                return getCityNameFromAddress(addressComponents);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getGeoResults(String lat, String lng) throws IOException {
        return mapAPI.getGeoResults(lat, lng);
    }

    private String getCityNameFromAddress(JSONArray addressComponents) throws JSONException {
        for (int i = 0; i < addressComponents.length(); i++) {
            JSONObject address = addressComponents.getJSONObject(i);
            String longNameforCity = address.getString("long_name");
            JSONArray types = address.getJSONArray("types");
            String type = types.getString(0);
            if (!TextUtils.isEmpty(longNameforCity)) {
                if (type.equalsIgnoreCase("administrative_area_level_1") && longNameforCity.isEmpty()) {
                    return longNameforCity;
                }
                if (type.equalsIgnoreCase("locality")) {
                    return longNameforCity;
                }
                if (type.equalsIgnoreCase("postal_town")) {
                    return longNameforCity;
                }
                if (type.equalsIgnoreCase("country") && longNameforCity.isEmpty()) {
                    return longNameforCity;
                }
            }
        }
        return null;
    }
}
