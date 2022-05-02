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
    public void getUserList() {
        assertTrue(userController.allUsers().size() >= 2);
    }

    @Test
    public void createUser(){
        // given:
        Map<String, String> body = new HashMap<>();
        body.put("name", "Bob Dylan");
        body.put("email", "test42@gmail.com");

        // when :
        User user = userController.createUser(body);

        // then :
        assertEquals(user.getEmail(), "test42@gmail.com");
        assertEquals(user.getName(), "Bob Dylan");
    }
}
