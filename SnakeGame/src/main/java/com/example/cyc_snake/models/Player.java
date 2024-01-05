package com.example.cyc_snake.models;

/**
 * Represents a player in the Snake Game application.
 *
 * <p>
 * The {@code Player} class encapsulates information about a player, including the player's name, score, and game time.
 * Instances of this class are used to store and retrieve player data within the game.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-29
 */
public class Player {

    private String playerName;
    private Integer score;
    private Long gameTime;

    /**
     * Constructs a new instance of the {@code Player} class with the specified parameters.
     *
     * @param playerName The name of the player.
     * @param score      The score achieved by the player.
     * @param gameTime   The total game time played by the player.
     */
    public Player(String playerName, Integer score, Long gameTime) {
        this.playerName = playerName;
        this.score = score;
        this.gameTime = gameTime;
    }

    /**
     * Gets the name of the player.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the score achieved by the player.
     *
     * @return The player's score.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Gets the total game time played by the player.
     *
     * @return The total game time in milliseconds.
     */
    public Long getGameTime() {
        return gameTime;
    }

    // Setter methods can be added here if needed
}
