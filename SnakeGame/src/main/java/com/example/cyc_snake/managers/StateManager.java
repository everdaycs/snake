package com.example.cyc_snake.managers;

import com.example.cyc_snake.utils.PlayMusic;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Manages the state of the Snake Game, including background color, snake color, and player information.
 *
 * @version 1.0
 * @since 2023-11-21
 */
public class StateManager {

    private double bgmVolume = 50; // Default volume
    private String playerName;
    private static final Color CORAL = Color.web("#FF7F50");
    private static final Color MINT_GREEN = Color.web("#98FF98");
    private static final Color DEEP_SEA_BLUE = Color.web("#00688B");
    private static final Color SOFT_YELLOW = Color.web("#FFFF99");
    private static final Color DEEP_PURPLE = Color.web("#800080");
    private static final Color TAUPE = Color.web("#483C32");
    private static final Color POWDER_BLUE = Color.web("#B0E0E6");
    private static final Color CREAM = Color.web("#FFFDD0");
    private static final Color BRIGHTBLUE = Color.web("#0000FF");
    private Color backgroundColor;
    private Color snakeColor;
    private final Map<Color, Color> snakeColorMap;
    private List<Consumer<Color>> backgroundColorChangeListeners = new ArrayList<>();
    private List<Consumer<Color>> snakeColorChangeListeners = new ArrayList<>();

    /**
     * Constructs a new instance of the {@code StateManager} class.
     * Initializes default colors and the color mapping.
     */
    public StateManager() {
        backgroundColor = Color.WHITE; // Default background color
        snakeColor = Color.GREEN;
        snakeColorMap = new HashMap<>();
        initializeColorMap();
    }

    /**
     * Initializes the color mapping for snake colors based on background colors.
     */
    public void initializeColorMap() {
        snakeColorMap.put(Color.WHITE, Color.GREEN);
        snakeColorMap.put(Color.SKYBLUE, Color.ORANGE);
        snakeColorMap.put(Color.PALEGREEN, Color.PINK);
        snakeColorMap.put(Color.LAVENDER, Color.YELLOWGREEN);
        snakeColorMap.put(CREAM, Color.DARKBLUE);
        snakeColorMap.put(Color.LIGHTGRAY, BRIGHTBLUE);
        snakeColorMap.put(CORAL, Color.NAVY);
        snakeColorMap.put(MINT_GREEN, Color.PURPLE);
        snakeColorMap.put(DEEP_SEA_BLUE, Color.GOLD);
        snakeColorMap.put(SOFT_YELLOW, Color.DARKGREEN);
        snakeColorMap.put(DEEP_PURPLE, Color.YELLOW);
        snakeColorMap.put(TAUPE, Color.LIMEGREEN);
        snakeColorMap.put(POWDER_BLUE, Color.BLACK);
    }

    /**
     * Sets the background color and notifies all listeners.
     *
     * @param color The new background color.
     */
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        notifyListeners(color);
        notifySnakeColorChangeListeners();
    }


    /**
     * Adds a listener for snake color changes.
     *
     * @param listener The listener to add.
     */
    public void addSnakeColorChangeListener(Consumer<Color> listener) {
        snakeColorChangeListeners.add(listener);
    }

    /**
     * Notifies all snake color change listeners about the updated snake color.
     */
    private void notifySnakeColorChangeListeners() {
        Color newSnakeColor = getSnakeColor(); // Get the new snake color based on the background color
        for (Consumer<Color> listener : snakeColorChangeListeners) {
            listener.accept(newSnakeColor);
        }
    }

    /**
     * Gets the snake color based on the current background color.
     *
     * @return The snake color.
     */
    public Color getSnakeColor() {
        return snakeColorMap.getOrDefault(backgroundColor, Color.GREEN);
    }

    /**
     * Gets the current background music (BGM) volume.
     *
     * @return The BGM volume.
     */
    public double getBgmVolume() {
        return bgmVolume;
    }

    /**
     * Adds a background color change listener.
     *
     * @param listener The listener to add.
     */
    public void addBackgroundColorChangeListener(Consumer<Color> listener) {
        backgroundColorChangeListeners.add(listener);
    }


    /**
     * Notifies all listeners that the background color has changed.
     *
     * @param color The new background color.
     */
    private void notifyListeners(Color color) {
        for (Consumer<Color> listener : backgroundColorChangeListeners) {
            listener.accept(color);
        }
    }

    /**
     * Sets the player name.
     *
     * @param playerName The player name to set.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        // Additional logic, such as notifying listeners that the player name has changed, can be added here
    }

    /**
     * Gets the player name.
     *
     * @return The player name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the current background color.
     *
     * @return The background color.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
