package com.example.cyc_snake.controllers;

import com.example.cyc_snake.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for managing the Game Information view in the Snake Game application.
 *
 * <p>
 * The {@code GameinforController} is responsible for handling user interactions and actions related to the
 * display of game information. It includes methods for initializing the controller, handling the back button action,
 * and setting a reference to the main application.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-30
 */
public class GameinforController {

    @FXML
    private AnchorPane backgroundPane;

    @FXML
    private Button backToInitializingButton;

    private MainApp mainApp; // Reference to the MainApp

    /**
     * Default constructor for the GameinforController.
     * This constructor is automatically called by JavaFX during the controller initialization.
     * It is recommended not to include parameters in this constructor as JavaFX initializes controllers using this constructor.
     */
    public GameinforController() {
        // Default constructor
        // Note: Avoid including parameters in this constructor as JavaFX initializes controllers using this constructor.
    }

    /**
     * Initializes the controller after FXML loading.
     * This method is automatically called by JavaFX after the FXML file is loaded.
     * Any necessary initialization code can be placed here.
     */
    @FXML
    private void initialize() {
        // Initialization code, if any
    }

    /**
     * Sets the reference to the MainApp.
     *
     * @param mainApp The MainApp instance to be set.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handles the back button action by navigating back to the initializing view.
     *
     * @implNote This method is called when the "Back to InitializingView" button is clicked.
     */
    @FXML
    private void handleBackToInitializing() {
        // Handle the logic for going back to the initializing view
        mainApp.initializing();
    }
}