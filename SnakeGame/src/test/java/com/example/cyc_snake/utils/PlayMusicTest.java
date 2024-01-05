package com.example.cyc_snake.utils;

/**
 * @author wjscyc
 * @ClassName PlayMusicTest
 * @date: 15/12/2023 15:43
 * @Version: V1.0
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static org.mockito.Mockito.*;

class PlayMusicTest {

    @Mock
    private Media media;
    @Mock
    private MediaPlayer mediaPlayer;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        // Assume that the constructor of MediaPlayer returns a mocked MediaPlayer object
//        whenNew(MediaPlayer.class).withAnyArguments().thenReturn(mediaPlayer);
//    }


    // More tests...
}
