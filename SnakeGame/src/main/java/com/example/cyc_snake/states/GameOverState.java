package com.example.cyc_snake.states;

import com.example.cyc_snake.models.GameModel;

/**
 * Represents the game state when the game is over in the Snake Game.
 * The {@code GameOverState} class implements the {@code GameState} interface and provides specific behavior
 * for handling updates and user input during the game over state.
 *
 * <p>
 * In this state, the game is not actively running, and the player can perform specific actions, such as resetting
 * the game by pressing a designated key, for example, the 'R' key.
 * </p>
 *
 * @version 1.0
 * @since 2023-12-12
 */
public class GameOverState implements GameState {
    /**
     * Default constructor for the {@code GameOverState} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public GameOverState() {
        // Default constructor provided for default instantiation.
    }

    /**
     * Updates the game logic during the game over state.
     *
     * @param context The {@code GameModel} representing the game state and data.
     */
    @Override
    public void update(GameModel context) {
        // No active updates during the game over state
    }

    /**
     * Handles user input during the game over state.
     * In this state, pressing the designated key ('R') resets the game.
     *
     * @param context The {@code GameModel} representing the game state and data.
     * @param input   The input character representing the user's action.
     */
    @Override
    public void handleInput(GameModel context, char input) {
        // Handle input during game over state, e.g., reset game on 'R' key press
        if (input == 'R') {
            context.resetGame();
        }
    }
}
