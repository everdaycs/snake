package com.example.cyc_snake.models;

/**
 * Represents an apple that boosts the score when consumed by the snake.
 * Implements the {@link Apple} interface.
 *
 * @author wjscyc
 * @version V1.0
 * @since 18/12/2023
 */
public class ScoreBoostApple implements Apple {

    private int x, y;

    /**
     * Constructs a {@code ScoreBoostApple} instance with the specified coordinates.
     *
     * @param x The x-coordinate of the apple.
     * @param y The y-coordinate of the apple.
     */
    public ScoreBoostApple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Applies the score boost effect to the provided {@link GameModel} instance.
     * Increases the score by a fixed amount.
     *
     * @param gameModel The game model to apply the effect on.
     */
    public void applyEffect(GameModel gameModel) {
        gameModel.increaseScore(3);
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
