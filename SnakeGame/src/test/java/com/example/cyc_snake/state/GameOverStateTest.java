package com.example.cyc_snake.state;

import com.example.cyc_snake.models.GameModel;
import com.example.cyc_snake.states.GameOverState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * Test class for the {@link GameOverState} class.
 * This class contains test methods to verify the behavior of the {@link GameOverState} state in the Snake Game.
 *
 * <p>
 * The tests focus on handling input to reset the game and ensuring that the {@link GameModel#resetGame()} method is
 * correctly called when the appropriate input ('R') is provided.
 * </p>
 *
 * @author wjscyc
 * @version 1.0
 * @since 2023-12-15
 */
class GameOverStateTest {

    private GameModel gameModel;
    private GameOverState gameOverState;

    @BeforeEach
    void setUp() {
        gameModel = mock(GameModel.class);
        gameOverState = new GameOverState();
    }

    /**
     * Test method for handling input to reset the game in the GameOverState.
     * This test verifies that the {@link GameOverState#handleInput(GameModel, char)} method correctly calls
     * {@link GameModel#resetGame()} when the appropriate input ('R') is provided.
     */
    @Test
    void testHandleInputResetGame() {
        gameOverState.handleInput(gameModel, 'R');
        verify(gameModel).resetGame();
    }
}
