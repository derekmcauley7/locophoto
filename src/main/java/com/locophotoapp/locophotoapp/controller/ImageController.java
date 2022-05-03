package com.locophotoapp.locophotoapp.controller;

import com.locophotoapp.locophotoapp.bean.Image;
import com.locophotoapp.locophotoapp.map.Map;
import com.locophotoapp.locophotoapp.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private Map map;

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @GetMapping("/images")
    public List<Image> getImages() {
        logger.info("Getting All Images");
        return imageRepository.findAll();
    }

    @GetMapping("/allImages/{lat}/{lng}")
    List<Image> search(@PathVariable("lat") String lat, @PathVariable("lng") String lng) {
        String city = map.getCity(lat, lng);
        logger.info("Getting images for city " + city + ". Using lat : " + lat + " Long: " + lng);
        return imageRepository.findByCityContaining(city);
    }

}
