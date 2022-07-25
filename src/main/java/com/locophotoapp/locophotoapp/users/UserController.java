package com.locophotoapp.locophotoapp.users;

import com.locophotoapp.locophotoapp.users.User;
import com.locophotoapp.locophotoapp.users.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        String email = body.get("email");

        Optional<User> user1 = Optional.ofNullable(userRepository.findByEmail(email));

        return user1.orElse(
                userRepository.save(
                        new User(email,
                                name,
                                Optional.ofNullable(body.get("imageUrl"))
                                        .orElse("https://accessibility-checker.s3-eu-west-1.amazonaws.com/default.jpg"),
                                ""))
        );
    }

    @PostMapping(value="/updateUser")
    public User updateUser(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String bio = body.get("bio");
        String email = body.get("email");
        User user = userRepository.findByEmail(email);
        if(user != null) {
            user.setBio(bio);
            user.setName(name);
            userRepository.save(user);
            return  user;
        }
        return userRepository.save(new User(email, name, "" , bio));
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id){
        return userRepository.findById(Integer.valueOf(id)).orElseThrow(() ->  new IllegalStateException("User not found"));
    }

    @GetMapping(value="/user/email/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userRepository.findByEmail(email);
    }

}
