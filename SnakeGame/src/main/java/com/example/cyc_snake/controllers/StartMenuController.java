package com.example.cyc_snake.controllers;

import javafx.scene.media.MediaPlayer;
import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.cyc_snake.utils.PlayMusic.clickSound;

/**
 * Controls the behavior of the start menu.
 *
 * <p>
 * The {@code StartMenuController} class is responsible for handling user interactions and controlling
 * the behavior of the start menu. It manages actions such as starting the game, accessing settings,
 * and updating the background color of the menu.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-21
 */
public class StartMenuController {

    private MainApp mainApp;
    private StateManager stateManager;

    @FXML
    private ImageView imageView;

    @FXML
    private Button startButton;

    @FXML
    private Button settingsButton;

    @FXML
    private AnchorPane backgroundPane;

    private Stage primaryStage;

    private static MediaPlayer menuMediaPlayer;

    /**
     * Default constructor for the StartMenuController.
     * This constructor is automatically called by JavaFX during the controller initialization.
     * It is recommended not to include parameters in this constructor as JavaFX initializes controllers using this constructor.
     */
    public StartMenuController() {
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
     * Updates the background color of the menu.
     *
     * @param color The new background color.
     */
    private void updateBackgroundColor(Color color) {
        Platform.runLater(() -> {
            backgroundPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    /**
     * Handles the event when the "Start Game" button is clicked.
     */
    @FXML
    private void handleStartGame() throws IOException {
        clickSound();
        // Stop menu background music before starting the game
        mainApp.initializeGameViewController();
        mainApp.showGameView();
    }

    /**
     * Handles the event when the "Settings" button is clicked.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void handleSettings() throws IOException {
        clickSound();
        mainApp.showSettings();
    }

    /**
     * Sets the primary stage for this controller.
     *
     * @param stage The primary stage to be set.
     */
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
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
