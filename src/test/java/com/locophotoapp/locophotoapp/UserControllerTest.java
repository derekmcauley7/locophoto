package com.locophotoapp.locophotoapp;

import com.locophotoapp.locophotoapp.bean.User;
import com.locophotoapp.locophotoapp.controller.UserController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    protected UserController userController;

    @Test
    @Sql(value = {"/import_test_users.sql"})
    public void shouldGetAListOfUsers() {
        assertTrue(userController.allUsers().size() >= 2);
    }

    @Test
    public void shouldCreateAUser(){
        // given:
        Map<String, String> body = new HashMap<>();
        body.put("name", "Bob Dylan");
        body.put("email", "bobdylan@gmail.com");

        // when:
        User user = userController.createUser(body);

        // then:
        assertEquals(user.getEmail(), "bobdylan@gmail.com");
        assertEquals(user.getName(), "Bob Dylan");
    }

    @Test
    @Sql(value = {"/import_test_users2.sql"})
    public void shouldUpdateUser(){
        // given:
        String newName = "Ben Moore";
        String newBio = "About ben";
        User user = userController.allUsers().get(0);

        Map<String, String> body = new HashMap<>();
        body.put("email", user.getEmail());
        body.put("name", newName);
        body.put("bio", newBio);

        // when:
        User updateUser = userController.updateUser(body);

        // then:
        assertEquals(updateUser.getId(), user.getId());
        assertEquals(updateUser.getName(), newName);
        assertEquals(updateUser.getBio(), newBio);
    }

    @Test
    public void shouldFindUserByEmail(){
        // given:
        Map<String, String> body = new HashMap<>();
        body.put("name", "new user");
        body.put("email", "newuser@gmail.com");

        userController.createUser(body);

        // when:
        User userByEmail = userController.getUserByEmail("newuser@gmail.com");

        // then:
        assertEquals("newuser@gmail.com", userByEmail.getEmail());
        assertEquals("new user", userByEmail.getName());
    }

    @Test
    public void shouldGetUserByID(){
        // given:
        Map<String, String> body = new HashMap<>();
        body.put("name", "New UserID");
        body.put("email", "newiduser@gmail.com");

        User createdUser = userController.createUser(body);

        // when:
        User userByID = userController.getUserById(String.valueOf(createdUser.getId()));

        // then:
        assertEquals(createdUser.getId(), userByID.getId());
    }

    @Test
    public void shouldThrowExceptionWhenUserIsNotFound(){
        try {
            userController.getUserById("111111");

        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "User not found");
        }
    }
}
