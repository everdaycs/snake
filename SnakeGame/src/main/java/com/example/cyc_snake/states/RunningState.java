package com.example.cyc_snake.states;

import com.example.cyc_snake.models.GameModel;

/**
 * Represents the "Running" state in the Snake Game.
 * The {@code RunningState} class implements the {@code GameState} interface, providing methods to update the game
 * logic and handle input specific to the running state.
 *
 * <p>
 * In the "Running" state, the game model continuously updates the snake's movement, checks for collisions,
 * and manages the appearance and disappearance of special apples. It also handles the logic for reverting to the
 * default speed after a speed change.
 * </p>
 *
 * @version 1.0
 * @since 2023-12-12
 */
public class RunningState implements GameState {

    /**
     * Default constructor for the {@code RunningState} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public RunningState() {
        // Default constructor provided for default instantiation.
    }

    /**
     * Updates the game logic in the running state.
     * This method is responsible for moving the snake, checking for collisions, handling special apples, and managing speed changes.
     *
     * @param context The {@code GameModel} representing the game state and data.
     */
    @Override
    public void update(GameModel context) {
        if (context.isRunning()) {
            context.move();
            context.checkApple();
            context.checkCollisions();
            context.shrinkBorder();
        }

        // Check if a special apple exists and if its duration has passed
        if (context.specialAppleExists.get() && (System.currentTimeMillis() - context.specialAppleSpawnTime > context.SPECIAL_APPLE_DURATION)) {
            context.removeSpecialApple();
            if (context.isRunning()) {
                context.generateSpecialApple();
            }
        }

        // Check if the speed change should end
        if (context.speedChangeEndTime != 0 && System.currentTimeMillis() > context.speedChangeEndTime) {
            context.speedMultiplier.set(GameModel.INITIAL_SPEED); // Restore default speed
            context.speedChangeEndTime = 0; // Reset the speed change end time
        }
    }

    /**
     * Handles user input in the running state.
     * This method sets the direction of the snake based on the provided input character.
     *
     * @param context The {@code GameModel} representing the game state and data.
     * @param input   The input character representing the desired direction.
     */
    @Override
    public void handleInput(GameModel context, char input) {
        context.setDirection(input);
    }
}
