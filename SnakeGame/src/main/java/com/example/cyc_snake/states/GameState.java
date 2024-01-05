package com.example.cyc_snake.states;

import com.example.cyc_snake.models.GameModel;

/**
 * Represents the interface for different states in the Snake Game.
 * The {@code GameState} interface defines methods that must be implemented by specific game states to handle updates
 * and user input within the game model.
 *
 * <p>
 * Implementations of this interface, such as {@code RunningState} and {@code PausedState}, provide concrete
 * implementations for updating the game logic and handling user input based on the current state of the game.
 * </p>
 *
 * @version 1.0
 * @since 2023-12-12
 */
public interface GameState {

    /**
     * Updates the game logic based on the current state.
     *
     * @param context The {@code GameModel} representing the game state and data.
     */
    void update(GameModel context);

    /**
     * Handles user input based on the current state.
     *
     * @param context The {@code GameModel} representing the game state and data.
     * @param input   The input character representing the user's action.
     */
    void handleInput(GameModel context, char input);
}
