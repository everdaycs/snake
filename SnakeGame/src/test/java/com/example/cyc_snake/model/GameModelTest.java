package com.example.cyc_snake.model;

import com.example.cyc_snake.models.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the GameModel class.
 *
 * @author wjscyc
 * @ClassName GameModelTest
 * @date: 14/12/2023 21:28
 * @Version: V1.0
 */
class GameModelTest {

    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = GameModel.getInstance();
    }

    /**
     * Test method for the initial state of the game.
     */
    @Test
    void testInitialState() {
        gameModel.setRunning(true);
        assertTrue(gameModel.isRunning(), "Game should be running initially");
        assertFalse(gameModel.gameOverProperty().get(), "Game over should be false initially");
        assertEquals(GameModel.INITIAL_SPEED, gameModel.speedMultiplierProperty().get(), "Initial speed should be set");
    }

    /**
     * Test method for the movement of the snake.
     */
    @Test
    void testMove() {
        gameModel.setDirection('R');
        gameModel.move();
        // You should verify that the snake's head has moved to the right
    }

    /**
     * Test method for generating a new apple.
     */
    @Test
    void testNewApple() {
        gameModel.newApple();
        assertTrue(gameModel.getAppleX() >= 0 && gameModel.getAppleY() >= 0, "Apple should be within game boundaries");
    }

    /**
     * Test method for collision with itself.
     */
    @Test
    void testCollisionWithSelf() {
        // Set up a scenario where the snake collides with itself
        gameModel.checkCollisions();
        assertFalse(gameModel.isRunning(), "Game should not be running after collision with self");
    }

    /**
     * Test method for border shrinking.
     */
    @Test
    void testBorderShrink() {
        gameModel.shrinkBorder();
        // Verify that the borders have shrunk correctly
    }

    /**
     * Test method for eating an apple.
     */
    @Test
    void testEatApple() {
        gameModel.newApple();
        // Move the snake to the apple's position and check if the apple is eaten
    }

    /**
     * Test method for the effect of eating a special apple.
     */
    @Test
    void testSpecialAppleEffect() {
        gameModel.generateSpecialApple();
        assertTrue(gameModel.isSpecialAppleExists(), "Special apple should exist after being generated");
        // You can add additional logic to test the effect of eating a special apple
    }

    /**
     * Test method for the game over scenario.
     */
    @Test
    void testGameOver() {
        gameModel.checkCollisions();
        assertTrue(gameModel.gameOverProperty().get(), "Game should be over after collision");
    }

    // Add more tests as needed to cover different aspects of GameModel
}
