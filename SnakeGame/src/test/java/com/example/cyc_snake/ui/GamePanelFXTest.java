package com.example.cyc_snake.ui;

import com.example.cyc_snake.models.GameModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wjscyc
 * @ClassName GamePanelFXTest
 * @date: 15/12/2023 11:10
 * @Version: V1.0
 */
class GamePanelFXTest {

    private GamePanelFX gamePanelFX;
    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gamePanelFX = new GamePanelFX();
        gameModel = Mockito.mock(GameModel.class);
        gamePanelFX.setGameModel(gameModel);
    }

    @Test
    void setBackgroundColorTest() {
        Color testColor = Color.BLUE;
        gamePanelFX.setBackgroundColor(testColor);
        assertEquals(testColor, gamePanelFX.getBackgroundColor(), "Background color should be set to blue");
    }

    @Test
    void setSnakeColorTest() {
        Color testColor = Color.RED;
        gamePanelFX.setSnakeColor(testColor);
        assertEquals(testColor, gamePanelFX.getSnakeColor(), "Snake color should be set to red");
    }

    // Add more tests to cover different aspects of GamePanelFX
}
