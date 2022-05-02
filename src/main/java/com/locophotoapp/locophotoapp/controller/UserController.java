package com.locophotoapp.locophotoapp.controller;

import com.locophotoapp.locophotoapp.bean.User;
import com.locophotoapp.locophotoapp.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> allUsers(){
        return userRepository.findAll();
    }

}
