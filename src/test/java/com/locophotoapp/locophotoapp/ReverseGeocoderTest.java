package com.locophotoapp.locophotoapp;

import com.locophotoapp.locophotoapp.map.MapAPI;
import com.locophotoapp.locophotoapp.map.ReverseGeocoderImpl;
import com.amazonaws.util.IOUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.annotation.Resource;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReverseGeocoderTest {

    MapAPI mapAPI = Mockito.mock(MapAPI.class);

    @Resource
    ReverseGeocoderImpl map = new ReverseGeocoderImpl(mapAPI);

    @Test
    public void shouldReturnCityDublinFromJson() throws Exception {
        //given
        InputStream is = new FileInputStream("src/main/resources/googlemapsDublin.json");
        String jsonTxt = IOUtils.toString(is);
        JSONObject jsonObj = new JSONObject(jsonTxt);

        //when
        when(mapAPI.getGeoResults(any(), any())).thenReturn(jsonObj.toString());
        String city = map.getCity("53.3498","6.2603");

        //then
        assertEquals("Dublin", city);
    }

    @Test
    public void shouldReturnCityLondonFromJson() throws Exception {
        //given
        InputStream is = new FileInputStream("src/main/resources/googlemapsLondon.json");
        String jsonTxt = IOUtils.toString(is);
        JSONObject jsonObj = new JSONObject(jsonTxt);

        //when
        when(mapAPI.getGeoResults(any(), any())).thenReturn(jsonObj.toString());
        String city = map.getCity("51.5072", "0.1276");

        //then
        assertEquals("London", city);
    }
}
