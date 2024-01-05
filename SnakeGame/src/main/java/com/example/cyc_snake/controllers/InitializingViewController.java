package com.example.cyc_snake.controllers;

import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;

import static com.example.cyc_snake.utils.PlayMusic.clickSound;

/**
 * Controls the behavior of the initializing view.
 *
 * <p>
 * The {@code InitializingViewController} class manages the initializing view, allowing the player
 * to start a new game or select a historical player.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-28
 */
public class InitializingViewController {
    private MainApp mainApp;

    private StateManager stateManager;

    @FXML
    private TextField nameInput;

    @FXML
    private AnchorPane backgroundPane;


    @FXML
    private AnchorPane setBackground;

    /**
     * Default constructor for the InitializingViewController.
     * This constructor is automatically called by JavaFX during the controller initialization.
     * It is recommended not to include parameters in this constructor as JavaFX initializes controllers using this constructor.
     */
    public InitializingViewController() {
        // Default constructor
        // Note: Avoid including parameters in this constructor as JavaFX initializes controllers using this constructor.
    }

    /**
     * Sets the state manager for this controller.
     *
     * @param stateManager The state manager to be set.
     */
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
        stateManager.addBackgroundColorChangeListener(this::updateBackgroundColor);
        if (stateManager.getBackgroundColor() != null) {
            updateBackgroundColor(stateManager.getBackgroundColor());
        }
    }


    /**
     * Updates the background color of the initializing view.
     *
     * @param color The new background color.
     */
    private void updateBackgroundColor(Color color) {
        Platform.runLater(() -> {
            setBackground.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    /**
     * Handles the event when the "Start Game" button is clicked.
     * Plays a click sound and navigates to the new player registration screen.
     */
    @FXML
    private void handleStartGame() {
        clickSound();
        mainApp.showNewPlayer();
    }


    /**
     * Handles the event when the "Select History Player" button is clicked.
     * Plays a click sound and shows the leaderboard screen.
     *
     * @throws IOException If an I/O error occurs during the process.
     */
    @FXML
    private void handleSelectHistoryPlayer() throws IOException {
        clickSound();
        mainApp.showLeaderBoard();
    }


    /**
     * Sets the main app for this controller.
     *
     * @param mainApp The main app to be set.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handles the event when the "Show Game Rules" button is clicked.
     * Plays a click sound and shows the game information screen.
     * Invoked by JavaFX when the associated FXML button is clicked.
     */
    @FXML
    private void handleShowGameInfo() {
        clickSound();
        mainApp.showGameInfo();
    }

}
