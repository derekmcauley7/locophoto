package com.locophotoapp.locophotoapp;

import com.locophotoapp.locophotoapp.controller.ImageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ImageControllerTest {

    @Autowired
    protected ImageController imageController;

    @Test
    @Sql(value = {"/import_test_images.sql"})
    public void shouldReturnAllUsers() {
        assertTrue(imageController.getImages().size() >= 2);
    }

}
