package com.example.cyc_snake.state;

import com.example.cyc_snake.models.GameModel;
import com.example.cyc_snake.states.PausedState;
import com.example.cyc_snake.states.RunningState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test class for the {@link PausedState} class.
 * This class contains test methods to verify the behavior of the {@link PausedState} state in the Snake Game.
 *
 * <p>
 * The tests focus on handling input to resume the game and ensuring that the game state transitions to
 * the {@link RunningState} when the appropriate input is provided.
 * </p>
 *
 * @author wjscyc
 * @version 1.0
 * @since 2023-12-15
 */
class PausedStateTest {

    private GameModel gameModel;
    private PausedState pausedState;

    @BeforeEach
    void setUp() {
        gameModel = mock(GameModel.class);
        pausedState = new PausedState();
    }

    /**
     * Test method for handling input to resume the game.
     * This test verifies that the {@link PausedState#handleInput(GameModel, char)} method correctly transitions the
     * game state to {@link RunningState} when the appropriate input ('P') is provided.
     */
    @Test
    void testHandleInputResumeGame() {
        pausedState.handleInput(gameModel, 'P');
        verify(gameModel).changeState(any(RunningState.class));
    }
}
