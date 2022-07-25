package com.locophotoapp.locophotoapp.images;

import com.locophotoapp.locophotoapp.users.User;
import com.locophotoapp.locophotoapp.maps.ReverseGeocoder;
import com.locophotoapp.locophotoapp.users.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ImageController {

    public ReverseGeocoder reverseGeocoder;

    private ImageRepository imageRepository;

    private UserController userController;

    public ImageController(ImageRepository imageRepository, ReverseGeocoder reverseGeocoder, UserController userController) {
        this.imageRepository = imageRepository;
        this.reverseGeocoder = reverseGeocoder;
        this.userController = userController;
    }

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @GetMapping("/images")
    public List<Image> getImages() {
        logger.info("Getting All Images");
        return imageRepository.findAll();
    }

    @GetMapping("/allImages/{lat}/{lng}")
    public List<Image> search(@PathVariable("lat") String lat, @PathVariable("lng") String lng) {
        String city = reverseGeocoder.getCity(lat, lng);
        logger.info("Getting images for city " + city + ". Using lat : " + lat + " Long: " + lng);
        return imageRepository.findByCityContaining(city);
    }

    @GetMapping("/userImages/{email}")
    public List<Image> searchByUser(@PathVariable("email") String email) {
        User user = userController.getUserByEmail(email);
        return imageRepository.findByUserID(Optional.ofNullable(String.valueOf(user.getId())).orElse("0"));
    }

}
