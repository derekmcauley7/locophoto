package com.locophotoapp.locophotoapp;

import com.locophotoapp.locophotoapp.bean.Image;
import com.locophotoapp.locophotoapp.controller.ImageController;
import com.locophotoapp.locophotoapp.map.MapImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ImageControllerTest {

    private MapImpl mapImpl = Mockito.mock(MapImpl.class);

    @Autowired
    protected ImageController imageController;

    @Test
    @Sql(value = {"/import_test_images.sql"})
    public void shouldReturnAllImages() {
        assertTrue(imageController.getImages().size() >= 2);
    }

    @Test
    @Sql(value = {"/import_test_images.sql"})
    public void shouldReturnImageUsingLatAndLong(){
        // given:
        imageController.map = mapImpl;
        Mockito.doReturn("Dublin").when(mapImpl).getCity("53.3498","6.2603");

        // when:
        Image image = imageController.search("53.3498","6.2603").get(0);

        // then:
        assertEquals("Dublin", image.getCity());
        assertEquals("2022-05-03", image.getDate());
        assertEquals(2, image.getViews());
    }

}
