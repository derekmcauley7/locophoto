package com.locophotoapp.locophotoapp.map;

import org.springframework.stereotype.Component;

@Component
public interface Map {
    String getCity(String lat, String lng);
}
