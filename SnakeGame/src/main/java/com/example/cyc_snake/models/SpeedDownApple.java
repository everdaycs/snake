package com.example.cyc_snake.models;

/**
 * Represents an apple that decreases the speed of the snake when consumed.
 * Implements the {@link Apple} interface.
 *
 * @author wjscyc
 * @version V1.0
 * @since 18/12/2023
 */
public class SpeedDownApple implements Apple {

    private int x, y;

    /**
     * Constructs a {@code SpeedDownApple} instance with the specified coordinates.
     *
     * @param x The x-coordinate of the apple.
     * @param y The y-coordinate of the apple.
     */
    public SpeedDownApple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Applies the speed decrease effect to the provided {@link GameModel} instance.
     * Decreases the speed of the snake.
     *
     * @param gameModel The game model to apply the effect on.
     */
    public void applyEffect(GameModel gameModel) {
        gameModel.increaseScore(1);
        gameModel.decreaseSpeed();
    }

    /**
     * Gets the x-coordinate of the apple.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the apple.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }
}
