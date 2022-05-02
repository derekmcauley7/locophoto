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

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    protected UserController userController;

    @Test
    @Sql(value = {"/import_test_users.sql"})
    public void getUserList() {
        User testUser =  userController.allUsers().get(0);
        assertEquals(testUser.getName(), "Test User");
        assertEquals(testUser.getEmail(), "test@mail.com");
    }
}
