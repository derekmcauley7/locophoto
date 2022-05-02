package com.locophotoapp.locophotoapp.controller;

import com.locophotoapp.locophotoapp.bean.User;
import com.locophotoapp.locophotoapp.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Resource
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value="/user")
    public User createUser(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        Optional<String> imageUrl = Optional.ofNullable(body.get("imageUrl"));

        if (!imageUrl.isPresent()) {
            imageUrl = Optional.of("https://accessibility-checker.s3-eu-west-1.amazonaws.com/default.jpg");
        }
        String email = body.get("email");

        Optional<User> user1 = Optional.ofNullable(userRepository.findDistinctEmail(email));

        return user1.orElse(userRepository.save(new User(email, name, imageUrl.get() , "")));
    }

}
