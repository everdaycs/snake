package com.example.cyc_snake.controllers;

import com.example.cyc_snake.DatabaseConnection;
import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.List;

import static com.example.cyc_snake.utils.PlayMusic.clickSound;

/**
 * Controls the behavior of the history player view.
 *
 * <p>
 * The {@code HistoryPlayerViewController} class manages user interactions in the history player view. It allows users
 * to select a player from the list, delete player data, and navigate back to the new player view. The class provides
 * methods for handling these interactions and updating the UI accordingly.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-28
 */
public class HistoryPlayerViewController {

    @FXML
    private ListView<String> playerList;

    @FXML
    private AnchorPane backGround;

    private DatabaseConnection databaseConnection;
    private StateManager stateManager;
    private MainApp mainApp;

    /**
     * Default constructor for the HistoryPlayerViewController.
     * This constructor is automatically called by JavaFX during the controller initialization.
     * It is recommended not to include parameters in this constructor as JavaFX initializes controllers using this constructor.
     */
    public HistoryPlayerViewController() {
        // Default constructor
        // Note: Avoid including parameters in this constructor as JavaFX initializes controllers using this constructor.
    }

    /**
     * Initializes the history player view by loading unique player names into the list.
     */
    @FXML
    public void initialize() {
        databaseConnection = new DatabaseConnection();
        List<String> names = databaseConnection.getUniquePlayerNames();
        playerList.getItems().setAll(names);
    }

    /**
     * Handles the event when a player is selected from the list.
     */
    @FXML
    private void handleSelectPlayer() {
        clickSound();
        String selectedName = playerList.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            stateManager.setPlayerName(selectedName);
            mainApp.showStartMenu();
        }
    }

    /**
     * Handles the event when the "Delete Player" button is clicked.
     */
    @FXML
    private void handleDeletePlayer() {
        clickSound();
        String selectedName = playerList.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete the data of " + selectedName, ButtonType.YES, ButtonType.NO);
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    try {
                        databaseConnection.deletePlayer(selectedName);
                        playerList.getItems().remove(selectedName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Handle the exception, possibly by displaying an error dialog
                    }
                }
            });
        }
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
     * Updates the background color of the history player view.
     *
     * @param color The new background color.
     */
    private void updateBackgroundColor(Color color) {
        Platform.runLater(() -> {
            backGround.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
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
     * Handles the event when the "Back to Initialize" button is clicked.
     *
     * @param actionEvent The event triggered by the button click.
     */
    public void backToInitialize(ActionEvent actionEvent) {
        clickSound();
        mainApp.showNewPlayer();
    }
}
