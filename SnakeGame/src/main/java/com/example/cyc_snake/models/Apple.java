package com.example.cyc_snake.models;

/**
 * The {@code Apple} interface represents an apple in the snake game.
 * Apples have effects when consumed by the snake, and they are positioned
 * on the game board with x and y coordinates.
 *
 * Implementations of this interface should provide methods to apply effects
 * to the game model upon consumption and retrieve the x and y coordinates of the apple.
 *
 * @author wjscyc
 * @version V1.0
 * @since 18/12/2023
 */
public interface Apple {

    /**
     * Applies the effect of the apple to the specified {@link GameModel} instance.
     *
     * @param gameModel The game model on which to apply the effect.
     */
    void applyEffect(GameModel gameModel);

    /**
     * Gets the x-coordinate of the apple on the game board.
     *
     * @return The x-coordinate of the apple.
     */
    int getX();

    /**
     * Gets the y-coordinate of the apple on the game board.
     *
     * @return The y-coordinate of the apple.
     */
    int getY();
}
