package com.example.cyc_snake.models;

/**
 * Represents an apple that triggers a game over effect when consumed by the snake.
 * Implements the {@link Apple} interface.
 *
 * @author wjscyc
 * @version V1.0
 * @since 18/12/2023
 */
public class GameOverApple implements Apple {

    private int x, y;

    /**
     * Constructs a {@code GameOverApple} instance with the specified coordinates.
     *
     * @param x The x-coordinate of the apple.
     * @param y The y-coordinate of the apple.
     */
    public GameOverApple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Applies the game over effect to the provided {@link GameModel} instance.
     *
     * @param gameModel The game model to apply the effect on.
     */
    public void applyEffect(GameModel gameModel) {
        gameModel.setGameOver(true);
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
