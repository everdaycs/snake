package com.example.cyc_snake.controllers;

/**
 * @author wjscyc
 * @ClassName GameViewController
 * @date: 22/11/2023 22:47
 * @Version: V1.0
 */
import com.example.cyc_snake.managers.TimeObserver;
import com.example.cyc_snake.states.RunningState;
import com.example.cyc_snake.utils.PlayMusic;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import com.example.cyc_snake.DatabaseConnection;
import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import com.example.cyc_snake.models.GameModel;
import com.example.cyc_snake.ui.GamePanelFX;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static com.example.cyc_snake.utils.PlayMusic.*;

/**
 * Controls the game view, handling user input and updating the game loop.
 * This class manages the interaction between the game model and the game panel,
 * as well as handling key events for controlling the game.
 *
 * <p>
 * The {@code GameViewController} class is responsible for managing the game view, handling user input,
 * updating the game loop, and interacting with the game model. It controls the game's visual representation,
 * user interactions, and communication between different components of the game.
 * </p>
 *
 * <p>
 * The class includes methods for initializing the game view, starting and stopping the game loop, handling
 * key presses for controlling the snake's direction, and managing the interaction with the game model.
 * It also includes methods for showing and hiding the end game view, resetting the game state, and updating
 * the highest score. The class uses JavaFX components for creating the game interface.
 * </p>
 *
 * <strong>Key Responsibilities:</strong>
 * <ul>
 *   <li>Initializing and managing the game view.</li>
 *   <li>Handling user input and controlling the game loop.</li>
 *   <li>Interacting with the game model and updating the visual representation.</li>
 *   <li>Showcasing end game view, resetting game state, and updating the highest score.</li>
 * </ul>
 *
 *
 * <strong>JavaFX Components:</strong>
 * <ul>
 *   <li>{@code Label timeLabel}: Displays the elapsed time during gameplay.</li>
 *   <li>{@code Label scoreLabel}: Displays the current score during gameplay.</li>
 *   <li>{@code Label lengthLabel}: Displays the current length of the snake during gameplay.</li>
 *   <li>{@code Label highestScoreLabel}: Displays the highest score achieved in the Snake Game.</li>
 *   <li>{@code BorderPane gameAnchorPane}: Represents the main container for the game interface.</li>
 *   <li>{@code GamePanelFX gamePanelFX}: Represents the visual representation of the game.</li>
 * </ul>
 *
 *
 *
 * <strong>Methods:</strong>
 * <ul>
 *   <li>{@code initialize()}: Initializes the game view, sets up the game model, binds properties, and starts the game loop.</li>
 *   <li>{@code resetGame()}: Resets the game state, including pausing, resetting the model, and restarting the loop.</li>
 *   <li>{@code setMainApp(MainApp mainApp)}: Sets the main application instance.</li>
 *   <li>{@code setStateManager(StateManager stateManager)}: Sets the state manager for handling background color changes.</li>
 *   <li>{@code startGame()}: Starts the game by hiding the end game view and resetting the game model.</li>
 *   <li>{@code stopGameLoop()}: Stops the game loop.</li>
 *   <li>{@code startGameLoop()}: Starts the game loop.</li>
 *   <li>{@code handleKeyPressed(KeyEvent event)}: Handles key presses for controlling the snake's direction and pausing the game.</li>
 *   <li>{@code showEndGameView()}: Displays the end game view.</li>
 *   <li>{@code hideEndGameView()}: Hides the end game view.</li>
 *   <li>{@code getGameView()}: Retrieves the game view as a JavaFX Parent.</li>
 *   <li>{@code getPlayTime()}: Gets the total playtime of the current game.</li>
 * </ul>
 *
 *
 * @author scyyc19
 * @since 2023-11-22
 */
public class GameViewController implements TimeObserver{
    private List<TimeObserver> timeObservers = new ArrayList<>();

    /**
     * The JavaFX Label used to display the elapsed time in the Snake Game.
     * This label is part of the GameViewController and is updated to show the current time during gameplay.
     *
     * FXML annotation is used for injecting the timeLabel from the FXML file.
     *
     * @see GameViewController
     */
    @FXML
    public Label timeLabel;

    /**
     * The JavaFX Label used to display the current score in the Snake Game.
     * This label is part of the GameViewController and is updated to show the player's score during gameplay.
     *
     * @FXML annotation is used for injecting the scoreLabel from the FXML file.
     *
     * @see GameViewController
     */
    @FXML
    private Label scoreLabel;

    /**
     * The JavaFX Label used to display the current length of the snake in the Snake Game.
     * This label is part of the GameViewController and is updated to show the player's snake length during gameplay.
     *
     * @FXML annotation is used for injecting the lengthLabel from the FXML file.
     *
     * @see GameViewController
     */
    @FXML
    private Label lengthLabel;

    /**
     * Label for displaying the highest score in the game interface.
     */
    @FXML
    Label highestScoreLabel;

    /**
     * Stores the amount of paused time during game play.
     */
    private long pausedTime = 0;

    /**
     * Tracks the total game time elapsed.
     */
    private long gameTime;

    /**
     * Indicates whether the game is currently paused.
     */
    private boolean isPaused = false;

    /**
     * Controller for the end game view.
     */
    private EndGameViewController endGameViewController;

    /**
     * Stage for displaying the end game view.
     */
    private Stage endGameStage;

    /**
     * Flag to check if the end game view has been initialized.
     */
    private boolean endGameViewInitialized = false;

    /**
     * The main game area container in the FXML layout.
     */
    @FXML
    private BorderPane gameAnchorPane;

    /**
     * Manages the state of the game, including player data and game settings.
     */
    private StateManager stateManager;

    /**
     * Reference to the main application class.
     */
    private MainApp mainApp;

    /**
     * Model representing the game's logical structure and rules.
     */
    GameModel gameModel;

    /**
     * Custom JavaFX panel for displaying the game.
     */
    @FXML
    GamePanelFX gamePanelFX;

    /**
     * Tracks the total play time of the game.
     */
    private long playTime;

    /**
     * Indicates whether the second background music has been played.
     */
    private boolean isSecondBGMPlayed = false;

    /**
     * Timer for controlling game updates and rendering.
     */
    private AnimationTimer gameLoop;

    /**
     * Update interval for the game loop, defining how often the game state is updated.
     */
    private long updateInterval = 1000000000 / 10; // Default update frequency

    /**
     * Base update interval for the game, set to 10 updates per second.
     */
    private static final long BASE_UPDATE_INTERVAL = 1000000000 / 10;

    /**
     * Time at which the speed effect ends in the game.
     */
    private long speedEffectEndTime = 0;

    /**
     * Timestamp of the last game update.
     */
    private long lastUpdate = 0;

    /**
     * Media player for playing game background music.
     */
    private MediaPlayer gameMediaPlayer;


    /**
     * Default constructor for the GameViewController.
     * This constructor is automatically called by JavaFX during the controller initialization.
     * It is recommended not to include parameters in this constructor as JavaFX initializes controllers using this constructor.
     */
    public GameViewController() {
        // Default constructor
        // Note: Avoid including parameters in this constructor as JavaFX initializes controllers using this constructor.
    }

    /**
     * Adds a {@link TimeObserver} to the list of observers.
     * <p>
     * This method registers an observer that will be notified of time updates.
     * It allows for multiple observers to be added, each of which will receive
     * time updates when they occur.
     * </p>
     *
     * @param observer The {@link TimeObserver} to be added to the list of observers.
     *                 It cannot be null.
     */
    public void addTimeObserver(TimeObserver observer) {
        timeObservers.add(observer);
    }

    /**
     * Notifies all registered {@link TimeObserver} instances about a time update.
     * <p>
     * This method is called to update all registered observers with the current time.
     * Each observer's {@code onTimeUpdate} method will be invoked with the updated time.
     * </p>
     *
     * @param time The current time to be passed to the observers. This should be a
     *             long value representing a specific time measure (e.g., milliseconds since
     *             a certain event or epoch).
     */
    private void notifyTimeObservers(long time) {
        for (TimeObserver observer : timeObservers) {
            observer.onTimeUpdate(time);
        }
    }



    /**
     * Initializes the game view, including setting up the game model,
     * binding properties, and starting the game loop.
     *
     * @throws IOException If an error occurs while loading FXML or initializing the end game view.
     */
    @FXML
    private void initialize() throws IOException {
        // Ensure that gameModel and gamePanelFX are not null
        gameModel = GameModel.getInstance(); // 替代 new GameModel()
        gamePanelFX.setGameModel(gameModel); // Pass the game model to the game panel
        addTimeObserver(gameModel);
        scoreLabel.textProperty().bind(gameModel.scoreProperty().asString("Score: %d"));
        lengthLabel.textProperty().bind(gameModel.lengthProperty().asString("Length: %d"));
        // Listener for changes in speed multiplier property of the game model
        gameModel.speedMultiplierProperty().addListener((observable, oldValue, newValue) -> {
            // Adjust game update frequency based on the new speed multiplier
            updateInterval = (long) (BASE_UPDATE_INTERVAL / newValue.doubleValue());
            // No need to restart the game loop; the next handle call will automatically use the new updateInterval
        });

        timeLabel.setText("Playtime: 0");
        startGameLoop();
        gameModel.scoreProperty().addListener((obs, oldScore, newScore) -> {
            if (newScore.intValue() > oldScore.intValue()) {
                eatAppleSound();
                // Stop the first BGM and play the second BGM when the length reaches 15, but only execute once
                // When the length reaches a specific value, stop the first background music (BGM) and play the second BGM, but execute this logic only once
                if (newScore.intValue() > 15 && !isSecondBGMPlayed) {
                    if (firstBGM != null && firstBGM.getStatus() == MediaPlayer.Status.PLAYING) {
                        disposeBGM1();
                    }
                    play2thBGM();
                    isSecondBGMPlayed = true;  // Mark that the second BGM has been played
                }
            }
        });

    }
    /**
     * Resets the game state by stopping the game loop and initializing a new game.
     */
    public void resetGame() {
        stopGameLoop();

        isPaused = false; // new: Game pause state
        isSecondBGMPlayed = false;
        playTime = 0;
        pausedTime = 0;
        gameModel.resetGame();
        gameModel.changeState(new RunningState());
        gamePanelFX.setGameModel(gameModel);
        gamePanelFX.draw();

        startGameLoop();
    }
    /**
     * Initializes the end game view.
     *
     * @throws IOException If an error occurs during initialization.
     */
    private void initializeEndGameView() throws IOException {
        if (!endGameViewInitialized) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/EndGameView.fxml"));
                Parent endGameRoot = loader.load();
                EndGameViewController endGameViewController = loader.getController();
                endGameViewController.setGameModel(gameModel);
                endGameViewController.setMainApp(this.mainApp);
                endGameViewController.setStateManager(stateManager);
                endGameStage = new Stage();
                endGameStage.setScene(new Scene(endGameRoot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        endGameViewInitialized = true;
    }
    /**
     * Sets the update interval for the game loop.
     *
     * @param interval The new update interval in nanoseconds.
     */
    // Method used to adjust the frequency of game updates
    private void setUpdateInterval(long interval) {
        this.updateInterval = interval;
        this.speedEffectEndTime = System.nanoTime() + 5_000_000_000L; // Set the end time 5 seconds later

    }

    /**
     * Checks and resets the speed effect based on the current time.
     *
     * @param now The current time in nanoseconds.
     */
    // This method is called in the handle method of gameLoop to check if the speed needs to be restored
    private void checkAndResetSpeed(long now) {
        if (speedEffectEndTime != 0 && now > speedEffectEndTime) {
            this.updateInterval = 1000000000 / 10; // Restore the default update frequency
            this.speedEffectEndTime = 0; // Reset the end time of the speed effect
        }
    }

    /**
     * Sets the main application instance.
     *
     * @param mainApp The MainApp instance.
     * @throws IOException If an error occurs during initialization.
     */
    public void setMainApp(MainApp mainApp) throws IOException {
        this.mainApp = mainApp;
    }

    /**
     * Sets the state manager for handling background color changes.
     *
     * @param stateManager The StateManager instance.
     * @throws IOException If an error occurs during initialization.
     */
    public void setStateManager(StateManager stateManager) throws IOException {
        this.stateManager = stateManager;
        this.stateManager.addBackgroundColorChangeListener(this::updateBackgroundColor);
        this.stateManager.addSnakeColorChangeListener(this::updateSnackColor);
        if (stateManager.getBackgroundColor() != null) {
            updateBackgroundColor(stateManager.getBackgroundColor());
            updateSnackColor(stateManager.getSnakeColor());
        }
        initializeEndGameView();
        gameModel.gameOverProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                DatabaseConnection.saveGameRecord(stateManager.getPlayerName(), gameModel.getScore(), getPlayTime());
                showEndGameView();
                // Trigger the sound effect for game over
                gameOverSound();
                if (firstBGM != null && firstBGM.getStatus() == MediaPlayer.Status.PLAYING) {
                    disposeBGM1();
                }
                if (secondBGM != null && secondBGM.getStatus() == MediaPlayer.Status.PLAYING) {
                    disposeBGM2();
                }
            }
        });

    }


    private void updateBackgroundColor(Color color) {
        Platform.runLater(() -> {
            if (gamePanelFX != null) {
                gamePanelFX.setBackgroundColor(color);
            }
        });
        Platform.runLater(() -> {
            gameAnchorPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }
    private void updateSnackColor(Color color) {
        Platform.runLater(() -> {
            if (gamePanelFX != null) {
                gamePanelFX.setSnakeColor(color);
            }
        });
    }

    /**
     * Starts the game by hiding the end game view and resetting the game model.
     */
    public void startGame() {
        hideEndGameView();
        gameModel.resetGame();
    }


    /**
     * Stops the game loop.
     */
    public void stopGameLoop() {
        gameLoop.stop();
        pausedTime = playTime;
    }
    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            private long startTime = -1;
            private long elapsedSeconds;
            @Override
            public void handle(long now) {
                if (isPaused) {
                    return; // If the game is paused, do not perform updates and rendering
                }
                if (startTime < 0) {
                    startTime = now - pausedTime * 1_000_000_000;
                }

                // Update the time display
                elapsedSeconds = (now - startTime) / 1_000_000_000;
                timeLabel.setText(String.format("Time: %d", elapsedSeconds));

                // Update the total game time
                playTime = elapsedSeconds;

                notifyTimeObservers(playTime);
                // Check if the time interval between now and the last update is greater than the set interval
                if ((now - lastUpdate) >= updateInterval) {
                    gameModel.update(); // Update the game model state
                    gamePanelFX.draw(); // Redraw the view based on the model state
                    lastUpdate = now; // Update the last update time
                }
            }

        };
        gameLoop.start();
        gamePanelFX.requestFocusForGamePanel();
    }

    /**
     * Handles key presses for controlling the snake's direction.
     * Responds to arrow key events for left, up, right, and down directions.
     *
     * @param event The KeyEvent representing the pressed key.
     */
    @FXML
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                if(gameModel.getDirection() != 'R') gameModel.setDirection('L');
                break;
            case UP:
                if(gameModel.getDirection() != 'D') gameModel.setDirection('U');
                break;
            case RIGHT:
                if(gameModel.getDirection() != 'L') gameModel.setDirection('R');
                break;
            case DOWN:
                if(gameModel.getDirection() != 'U') gameModel.setDirection('D');
                break;
            case ESCAPE:
                isPaused = !isPaused;
                if (isPaused) {
                    stopGameLoop();
                } else {
                    startGameLoop();
                }
        }
    }

    private void showEndGameView() {
        if (!endGameStage.isShowing()) {
            endGameStage.show();
        }
    }

    /**
     * Hides the end game view.
     */
    public void hideEndGameView() {
        if (endGameStage != null) {
            endGameStage.hide();
        }
    }

    /**
     * Gets the game view, which is the parent container for the game components.
     *
     * @return The game view as a Parent.
     */
    public Parent getGameView() {
        return gameAnchorPane;
    }



    /**
     * Gets the total playtime of the current game.
     *
     * @return The total playtime in seconds.
     */
    public long getPlayTime() {
        return playTime;
    }

    @Override
    public void onTimeUpdate(long time) {
        playTime = time;
    }
}
