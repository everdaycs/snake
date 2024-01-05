package com.example.cyc_snake;

import com.example.cyc_snake.adapters.*;
import com.example.cyc_snake.controllers.*;
import com.example.cyc_snake.managers.StateManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.cyc_snake.utils.PlayMusic.playGameBGM;

/**
 * The main class representing the Snake Game application.
 * This class extends the JavaFX Application class and serves as the entry point for the JavaFX application.
 *
 * <p>
 * The {@code MainApp} class is responsible for initializing and managing different views of the Snake Game.
 * It includes methods for showing the game, leaderboard, new player, start menu, game information, and settings views.
 * </p>
 *
 * @author wjscyc
 * @version 1.0
 * @since 21/11/2023
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private StateManager stateManager;
    private GameViewController gameViewController;
    private HistoryPlayerViewController historyPlayerViewController;
    private LeaderboardViewController leaderboardController;
    private EndGameViewController endGameViewController;
    private Parent gameViewRoot;
    private Parent historyPlayerViewRoot;
    private Scene gameScene; // New game scene

    /**
     * Default constructor for the {@code MainApp} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public MainApp() {
        // Default constructor provided for default instantiation.
    }

    /**
     * The entry point for the JavaFX application.
     * Initializes the primary stage, state manager, and the game view controller.
     * Creates the game scene and starts with the initializing view.
     *
     * @param primaryStage The primary stage for the application.
     * @throws IOException If an error occurs during initialization.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.stateManager = new StateManager();
        initializing();
    }

    /**
     * Shows the history player information view.
     * Loads the FXML file, initializes the controller, and sets up the primary stage.
     *
     * @throws IOException If an error occurs during loading.
     */
    public void showHistoryPlayer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/HistoryPlayerView.fxml"));
        this.historyPlayerViewRoot = loader.load();
        this.historyPlayerViewController = loader.getController();

        HistoryPlayerViewAdapter adapter = new HistoryPlayerViewAdapter(historyPlayerViewController, this.stateManager, this);
        adapter.initializeController();

        primaryStage.setTitle("historyPlayer");
        primaryStage.setScene(new Scene(historyPlayerViewRoot));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * Initializes the game view controller.
     * Loads the FXML file, initializes the controller, and sets up the game view root.
     *
     * @throws IOException If an error occurs during loading.
     */
    public void initializeGameViewController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/GameView.fxml"));
        this.gameViewRoot = loader.load();
        this.gameViewController = loader.getController();
        this.gameViewController.setMainApp(this);
        this.gameViewController.setStateManager(this.stateManager);
        this.gameScene = new Scene(gameViewRoot); // Create the game scene
    }

    /**
     * Shows the game view.
     * Resets and starts the game, plays the game background music, and sets up the primary stage.
     */
    public void showGameView() {
        gameViewController.resetGame();
        gameViewController.startGame();
        playGameBGM();
        primaryStage.setScene(gameScene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * Shows the leaderboard view.
     * Loads the FXML file, initializes the controller, and sets up the primary stage.
     */
    public void showLeaderBoard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/Leaderboard.fxml"));
            Parent root = loader.load();

            LeaderboardViewController controller = loader.getController();

            LeaderboardViewAdapter adapter = new LeaderboardViewAdapter(controller, this.stateManager, this);
            adapter.initializeController();

            primaryStage.setTitle("SnakeGame Leaderboard");
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the new player view.
     * Loads the FXML file, initializes the controller, and sets up the primary stage.
     */
    public void showNewPlayer() {
        try {
            FXMLLoader newPlayerLoader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/NewPlayer.fxml"));
            NewPlayerController newPlayerController = new NewPlayerController();

            NewPlayerViewAdapter adapter = new NewPlayerViewAdapter(newPlayerController, this.stateManager, this);
            adapter.initializeController();

            newPlayerLoader.setController(newPlayerController);
            Parent newPlayerView = newPlayerLoader.load();

            primaryStage.setTitle("SnakeGame");
            primaryStage.setScene(new Scene(newPlayerView));
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the initializing view.
     * Loads the FXML file, initializes the controller, and sets up the primary stage.
     */
    public void initializing() {
        try {
            FXMLLoader initializeLoader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/InitializingView.fxml"));
            InitializingViewController initializingViewController = new InitializingViewController();

            InitializingViewAdapter adapter = new InitializingViewAdapter(initializingViewController, this.stateManager, this);
            adapter.initializeController();

            initializeLoader.setController(initializingViewController);
            Parent initializeView = initializeLoader.load();

            primaryStage.setTitle("SnakeGame");
            primaryStage.setScene(new Scene(initializeView));
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the start menu view.
     * Loads the FXML file, initializes the controller, and sets up the primary stage.
     */
    public void showStartMenu() {
        try {
            FXMLLoader startMenuLoader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/StartMenuView.fxml"));
            StartMenuController startMenuController = new StartMenuController();
            startMenuController.setStateManager(this.stateManager);
            startMenuController.setMainApp(this);
            startMenuLoader.setController(startMenuController);
            Parent startMenuView = startMenuLoader.load();

            primaryStage.setTitle("SnakeGame");
            primaryStage.setScene(new Scene(startMenuView));
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the game information view.
     * Loads the FXML file, initializes the controller, and sets up the primary stage.
     */
    public void showGameInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/GameInfo.fxml"));
            Parent gamerulesRoot = loader.load();

            GameinforController gameinforController = loader.getController();
            gameinforController.setMainApp(this);

            primaryStage.setTitle("SnakeGame Rules");
            primaryStage.setScene(new Scene(gamerulesRoot));
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the settings view.
     * Loads the FXML file, initializes the controller, and sets up the primary stage.
     *
     * @throws IOException If an error occurs during loading.
     */
    public void showSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/OptionView.fxml"));
        OptionViewController settingsController = new OptionViewController(this.stateManager);
        loader.setController(settingsController);

        OptionViewAdapter adapter = new OptionViewAdapter(settingsController, this.stateManager, this);
        adapter.initializeController();
        Parent settingsRoot = loader.load();

        primaryStage.setTitle("SnakeGame");
        primaryStage.setScene(new Scene(settingsRoot));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
