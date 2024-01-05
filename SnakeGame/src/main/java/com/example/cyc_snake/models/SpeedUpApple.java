package com.example.cyc_snake.models;

/**
 * Represents an apple that increases the speed of the snake when consumed.
 * Implements the {@link Apple} interface.
 *
 * @author wjscyc
 * @version V1.0
 * @since 18/12/2023
 */
public class SpeedUpApple implements Apple {

    private int x, y;

    /**
     * Constructs a {@code SpeedUpApple} instance with the specified coordinates.
     *
     * @param x The x-coordinate of the apple.
     * @param y The y-coordinate of the apple.
     */
    public SpeedUpApple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Applies the speed increase effect to the provided {@link GameModel} instance.
     * Increases the speed of the snake.
     *
     * @param gameModel The game model to apply the effect on.
     */
    public void applyEffect(GameModel gameModel) {
        gameModel.increaseScore(1);
        gameModel.increaseSpeed();
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
