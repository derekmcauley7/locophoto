package com.locophotoapp.locophotoapp.controller;

import com.locophotoapp.locophotoapp.bean.Image;
import com.locophotoapp.locophotoapp.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @GetMapping("/images")
    public List<Image> getImages() {
        logger.info("Getting All Images");
        return imageRepository.findAll();
    }

}
