package com.example.cyc_snake.controllers;

import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import com.example.cyc_snake.models.GameModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;

import static com.example.cyc_snake.utils.PlayMusic.clickSound;

/**
 * Controller class for the End Game View.
 *
 * <p>
 * The {@code EndGameViewController} class manages the user interface and actions related to the
 * end game screen. It displays the player's score, provides options to restart the game or go
 * back to the main menu, and updates the background color based on the application state.
 * </p>
 *
 * @author wjscyc
 * @version 1.0
 * @since 23/11/2023
 */
public class EndGameViewController {
    private MainApp mainApp;

    @FXML
    Label scoreLabel;

    @FXML
    private Button restartButton;

    @FXML
    private AnchorPane backGround;

    @FXML
    private Button backButton;

    private StateManager stateManager;

    private GameModel gameModel;

    /**
     * Default constructor for the {@code EndGameViewController} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public EndGameViewController() {
        // Default constructor provided for default instantiation.
    }

    /**
     * Sets the associated game model for the controller.
     *
     * @param gameModel The {@code GameModel} instance.
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        scoreLabel.textProperty().bind(gameModel.scoreProperty().asString("Your score: %d"));
    }

    /**
     * Sets the main application instance.
     *
     * @param mainApp The {@code MainApp} instance.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Sets the state manager for handling application state changes.
     *
     * @param stateManager The {@code StateManager} instance.
     */
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
        stateManager.addBackgroundColorChangeListener(this::updateBackgroundColor);
        if (stateManager.getBackgroundColor() != null) {
            updateBackgroundColor(stateManager.getBackgroundColor());
        }
    }

    /**
     * Updates the background color of the view.
     *
     * @param color The {@code Color} to set as the background.
     */
    private void updateBackgroundColor(Color color) {
        Platform.runLater(() -> {
            backGround.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    /**
     * Handles the restart button action to restart the game.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void handleRestart() throws IOException {
        clickSound();
        mainApp.showGameView();
    }

    /**
     * Handles the back button action to return to the main menu.
     */
    @FXML
    void handleBackToMenu() {
        clickSound();
        mainApp.showStartMenu();
    }

    /**
     * Sets the game over information, updating the score label.
     *
     * @param score The player's final score.
     */
    public void setGameOverInfo(int score) {
        scoreLabel.setText("Game Over! Your score: " + gameModel.getScore());
    }

    /**
     * Exit the game
     *
     * @param actionEvent The {@code ActionEvent} triggering the action.
     */
    public void exit(ActionEvent actionEvent) {
        clickSound();
        Platform.exit();
    }
}
