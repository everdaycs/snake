package com.example.cyc_snake.models;

/**
 * The {@code AppleFactory} class is responsible for creating instances of {@link Apple}.
 * It uses a factory method to create different types of apples based on the specified type.
 *
 * This class provides a static method for creating apples, and it should not be instantiated.
 *
 * @author wjscyc
 * @version V1.0
 * @since 18/12/2023
 */
public class AppleFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private AppleFactory() {
        // This class should not be instantiated.
    }

    /**
     * Creates an instance of {@link Apple} based on the specified type and coordinates.
     *
     * @param type The type of apple to create.
     * @param x    The x-coordinate of the apple on the game board.
     * @param y    The y-coordinate of the apple on the game board.
     * @return An instance of {@link Apple} with the specified type and coordinates.
     * @throws IllegalArgumentException If the specified apple type is unknown.
     */
    public static Apple createApple(GameModel.AppleType type, int x, int y) {
        switch (type) {
            case BLUE:
                return new SpeedUpApple(x, y);
            case BLACK:
                return new GameOverApple(x, y);
            case GREEN:
                return new SpeedDownApple(x, y);
            case GOLD:
                return new ScoreBoostApple(x, y);
            default:
                throw new IllegalArgumentException("Unknown apple type");
        }
    }
}
