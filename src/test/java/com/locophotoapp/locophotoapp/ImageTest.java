package com.locophotoapp.locophotoapp;

import com.locophotoapp.locophotoapp.images.Image;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageTest {

    @Test
    public void shouldIncreaseViews(){
        // given:
        Image image = new Image();

        // when:
        IntStream.range(0, 5)
                        .forEach(i -> image.increaseViews());

        // then:
        assertEquals(5, image.getViews());
    }

    @Test
    public void shouldIncreaseLikes(){
        // given:
        Image image = new Image();

        // when:
        IntStream.range(0, 4)
                .forEach(i -> image.increaseLikes());

        // then:
        assertEquals(4, image.getLikes());
    }

    @Test
    public void shouldDecreaseLikes(){
        // given:
        Image image = new Image();
        image.setLikes(10);

        // when:
        IntStream.range(0, 4)
                .forEach(i -> image.decreaseLikes());

        // then:
        assertEquals(6, image.getLikes());
    }
}
