package com.example.cyc_snake.controllers;

import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;

import static com.example.cyc_snake.utils.PlayMusic.clickSound;

/**
 * Controls the behavior of the new player view.
 *
 * <p>
 * The {@code NewPlayerController} class manages user interactions in the new player view. It allows users to
 * enter a player name and start a new game. The class provides methods for handling these interactions and updating
 * the UI accordingly.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-29
 */
public class NewPlayerController {
    private MainApp mainApp;
    private StateManager stateManager;

    @FXML
    private TextField nameInput;

    @FXML
    private AnchorPane setBackground;

    /**
     * Default constructor for the NewPlayerController.
     * This constructor is automatically called by JavaFX during the controller initialization.
     * It is recommended not to include parameters in this constructor as JavaFX initializes controllers using this constructor.
     */
    public NewPlayerController() {
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
     * Updates the background color of the new player view.
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
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void handleStartGame() throws IOException {
        clickSound();
        String playerName = nameInput.getText().trim();
        if (playerName.isEmpty()) {
            showAlert("Error", "Please enter the player name");
        } else {
            stateManager.setPlayerName(playerName);
            mainApp.showStartMenu();
        }
    }

    /**
     * Handles the event when the "Back to Initialize" button is clicked.
     */
    @FXML
    private void backToInitialize() {
        clickSound();
        mainApp.initializing();
    }

    /**
     * Shows an alert with the specified title and message.
     *
     * @param title   The title of the alert.
     * @param message The message content of the alert.
     */
    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    /**
     * Handles the event when the "Select History Player" button is clicked.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void handleSelectHistoryPlayer() throws IOException {
        clickSound();
        mainApp.showHistoryPlayer();
    }

    /**
     * Sets the main app for this controller.
     *
     * @param mainApp The main app to be set.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
