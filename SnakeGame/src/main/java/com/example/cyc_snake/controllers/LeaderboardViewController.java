package com.example.cyc_snake.controllers;

import com.example.cyc_snake.DatabaseConnection;
import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import com.example.cyc_snake.models.Player;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Comparator;

import static com.example.cyc_snake.utils.PlayMusic.clickSound;

/**
 * Controller class for the LeaderboardView.fxml file.
 *
 * <p>
 * The {@code LeaderboardViewController} class manages the interaction between the UI elements
 * defined in LeaderboardView.fxml and the underlying data and logic. It handles actions such as
 * sorting the leaderboard, updating the background color, downloading data, and navigating back
 * to the main menu.
 * </p>
 *
 * @author [Author Name]
 * @version 1.0
 * @since [Date]
 */
public class LeaderboardViewController {
    // Reference to the main application
    private MainApp mainApp;

    // Reference to the StateManager for handling background color changes
    private StateManager stateManager;

    // FXML-injected UI elements
    @FXML private TableView<Player> leaderboardTable;
    @FXML private TableColumn<Player, String> playerNameColumn;
    @FXML private TableColumn<Player, Integer> scoreColumn;
    @FXML private TableColumn<Player, Long> timeColumn;
    @FXML private AnchorPane setBackground;
    @FXML private Button BacktoMain;

    /**
     * Default constructor for the {@code LeaderboardViewController} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public LeaderboardViewController() {
        // Default constructor provided for default instantiation.
    }

    /**
     * Initializes the controller. This method is called automatically after the FXML file has been loaded.
     */
    public void initialize() {
        // Populate the leaderboard table with player data
        ObservableList<Player> players = getPlayerData();
        leaderboardTable.setItems(players);
        leaderboardTable.refresh(); // Immediately refresh TableView
    }

    /**
     * Sets the StateManager for handling background color changes and initializes the background color.
     *
     * @param stateManager The StateManager to set.
     */
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
        stateManager.addBackgroundColorChangeListener(this::updateBackgroundColor);
        if (stateManager.getBackgroundColor() != null) {
            updateBackgroundColor(stateManager.getBackgroundColor());
        }
    }

    /**
     * Updates the background color of the AnchorPane.
     *
     * @param color The new background color.
     */
    private void updateBackgroundColor(Color color) {
        Platform.runLater(() -> {
            setBackground.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    /**
     * Sorts the leaderboard by player score in descending order.
     */
    @FXML
    private void sortByScore() {
        clickSound();
        ObservableList<Player> sortedList = FXCollections.observableArrayList(leaderboardTable.getItems());
        sortedList.sort(Comparator.comparing(Player::getScore).reversed());
        leaderboardTable.setItems(sortedList);
    }

    /**
     * Sorts the leaderboard by player game time in descending order.
     */
    @FXML
    private void sortByTime() {
        clickSound();
        ObservableList<Player> sortedList = FXCollections.observableArrayList(leaderboardTable.getItems());
        sortedList.sort(Comparator.comparing(Player::getGameTime).reversed());
        leaderboardTable.setItems(sortedList);
    }

    /**
     * Gets player data for the leaderboard from the database.
     *
     * @return ObservableList of Player objects.
     */
    private ObservableList<Player> getPlayerData() {
        return FXCollections.observableArrayList(DatabaseConnection.getPlayerRankings());
    }

    /**
     * Handles the action event for navigating back to the main menu.
     *
     * @param actionEvent The action event triggered by the BacktoMain button.
     */
    public void handleBackToInitialize(ActionEvent actionEvent) {
        clickSound();
        mainApp.initializing();
    }

    /**
     * Sets the main application reference.
     *
     * @param mainApp The main application instance.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handles the action event for downloading data to a CSV file.
     *
     * @param actionEvent The action event triggered by the downloadTheData button.
     */
    public void downloadTheData(ActionEvent actionEvent) {
        clickSound();
        DatabaseConnection.exportDataToCSV("playerRankings.csv");
    }
}
