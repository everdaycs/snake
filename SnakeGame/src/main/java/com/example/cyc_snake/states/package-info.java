/**
 * Manages different states of the Snake Game application.
 *
 * <p>
 * The {@code com.example.cyc_snake.states} package contains classes implementing the {@code GameState} interface,
 * each representing a distinct state of the Snake Game. These states dictate specific game behaviors and interactions
 * based on the current game state, such as running, paused, or game over.
 * </p>
 *
 * <h2>Key States:</h2>
 * <ul>
 *   <li>{@code RunningState}: Represents the active state when the game is running, handling game updates and movements.</li>
 *   <li>{@code PausedState}: Represents the paused state, allowing users to pause the game and providing options for resuming.</li>
 *   <li>{@code GameOverState}: Represents the state when the game is over, offering actions like resetting the game.</li>
 * </ul>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Defining distinct behaviors and interactions for different game states.</li>
 *   <li>Handling user input and game updates based on the current state.</li>
 *   <li>Facilitating transitions between states for a coherent gaming experience.</li>
 * </ul>
 *
 * @version 1.0
 * @since 2023-12-12
 */
package com.example.cyc_snake.states;
