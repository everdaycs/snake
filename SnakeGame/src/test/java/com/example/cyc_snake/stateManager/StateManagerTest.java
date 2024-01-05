package com.example.cyc_snake.stateManager;

import com.example.cyc_snake.managers.StateManager;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wjscyc
 * @ClassName StateManageTest
 * @date: 15/12/2023 10:10
 * @Version: V1.0
 */
class StateManagerTest {

    private StateManager stateManager;

    @BeforeEach
    void setUp() {
        stateManager = new StateManager();
    }

    @Test
    void testDefaultBackgroundColor() {
        assertEquals(Color.WHITE, stateManager.getBackgroundColor(), "Default background color should be white");
    }

    @Test
    void testSetAndGetBackgroundColor() {
        Color testColor = Color.BLACK;
        stateManager.setBackgroundColor(testColor);
        assertEquals(testColor, stateManager.getBackgroundColor(), "Background color should be black after setting");
    }

    @Test
    void testDefaultSnakeColor() {
        assertEquals(Color.GREEN, stateManager.getSnakeColor(), "Default snake color should be green");
    }

    @Test
    void testSnakeColorChangeOnBackgroundColorChange() {
        Color backgroundColor = Color.WHITE;
        Color expectedSnakeColor = Color.GREEN;
        stateManager.setBackgroundColor(backgroundColor);
        assertEquals(expectedSnakeColor, stateManager.getSnakeColor(), "Snake color should change based on background color");
    }


    @Test
    void testSetAndGetPlayerName() {
        String testName = "Player1";
        stateManager.setPlayerName(testName);
        assertEquals(testName, stateManager.getPlayerName(), "Player name should match the set value");
    }

    // Add more tests for other functionalities like listeners, if needed
}
