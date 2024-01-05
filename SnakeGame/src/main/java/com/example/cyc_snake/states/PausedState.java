package com.example.cyc_snake.states;

import com.example.cyc_snake.models.GameModel;

/**
 * Represents the "Paused" state in the Snake Game.
 * The {@code PausedState} class implements the {@code GameState} interface, providing methods to handle user input
 * and update the game logic specific to the paused state.
 *
 * <p>
 * In the "Paused" state, the game is temporarily halted, and the {@code PausedState} class handles any input
 * to resume the game, transitioning to the running state when the appropriate input is received.
 * </p>
 *
 * @version 1.0
 * @since 2023-12-12
 */
public class PausedState implements GameState {

    /**
     * Default constructor for the {@code PausedState} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public PausedState() {
        // Default constructor provided for default instantiation.
    }

    /**
     * Updates the game logic in the paused state.
     * This method is intentionally left empty as no game logic needs to be continuously updated while the game is paused.
     *
     * @param context The {@code GameModel} representing the game state and data.
     */
    @Override
    public void update(GameModel context) {
        // No continuous updates needed during the paused state
    }

    /**
     * Handles user input in the paused state.
     * This method processes input during the paused state, such as the key to resume the game ('P' key in this case).
     *
     * @param context The {@code GameModel} representing the game state and data.
     * @param input   The input character representing the user's action.
     */
    @Override
    public void handleInput(GameModel context, char input) {
        // Handle input during the paused state, e.g., key to resume the game
        if (input == 'P') { // Assuming 'P' key is used to resume the game
            context.changeState(new RunningState());
        }
    }
}
