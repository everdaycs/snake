package com.example.cyc_snake.state;

import com.example.cyc_snake.models.GameModel;
import com.example.cyc_snake.states.RunningState;
import javafx.beans.property.SimpleBooleanProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.mockito.Mockito.*;
/**
 * Test class for the {@link RunningState} class.
 * This class contains test methods to verify the behavior of the {@link RunningState} state in the Snake Game.
 *
 * <p>
 * The tests focus on updating the game logic in the running state and ensuring that specific methods of the
 * {@link GameModel} are called as expected.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-12-xx (Update with the actual date)
 */
class RunningStateTest {

    @Spy
    private GameModel gameModel;

    private RunningState runningState;

    @BeforeEach
    void setUp() {
        // Remove the explicit instantiation of the object
        // gameModel = new GameModel();

        // Create a spy on the real instance of GameModel
        gameModel = spy(GameModel.getInstance());

        gameModel.setRunning(true);
        runningState = new RunningState();
        gameModel.specialAppleExists = new SimpleBooleanProperty(false);
        // Mock other required methods
    }

    /**
     * Test method for updating the game logic in the RunningState.
     * This test verifies that the {@link RunningState#update(GameModel)} method correctly triggers specific
     * methods in the {@link GameModel} when the game is running.
     */
    @Test
    void testUpdateGameLogic() {
        // Use when().thenReturn() for spy objects
        when(gameModel.isRunning()).thenReturn(true);

        runningState.update(gameModel);

        // Verify that methods are called
        verify(gameModel).move();
        verify(gameModel).checkApple();
        verify(gameModel).checkCollisions();
        verify(gameModel).shrinkBorder();
    }
}
