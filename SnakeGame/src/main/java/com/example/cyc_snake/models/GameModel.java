package com.example.cyc_snake.models;
import com.example.cyc_snake.managers.TimeObserver;
import com.example.cyc_snake.states.GameOverState;
import com.example.cyc_snake.states.GameState;
import com.example.cyc_snake.states.RunningState;
import javafx.beans.property.*;

import java.util.Random;

/**
 * Represents the game model for the Snake Game application.
 *
 * <p>
 * The {@code GameModel} class manages the state, logic, and properties of the Snake Game. It includes
 * functionality for handling player input, updating the game state, managing the snake, apples, and
 * special apples, checking collisions, and more.
 * </p>
 *
 * @author wjscyc
 * @version 1.0
 * @since 2023-11-21
 */
public class GameModel implements TimeObserver {
    private static GameModel instance;
    private GameState currentState;
    private GameState runningState = new RunningState();
    private GameState gameOverState = new GameOverState();

    private static final int MAX_SPEED = 15;

    /**
     * Represents the initial speed of the snake in the Snake Game.
     * <p>
     * The {@code INITIAL_SPEED} constant defines the initial speed of the snake when the game starts.
     * This value determines the movement speed of the snake in the absence of any speed adjustments.
     * </p>
     */
    public static final double INITIAL_SPEED = 0.75;
    private static final double MIN_SPEED = 0.25;

    /**
     * Represents the indication of border shrinking in the Snake.
     *
     * <p>
     * The {@code aboutToShrink} property is a boolean property that indicates whether the game borders are
     * about to shrink. It serves as a warning mechanism, giving advanced notice before the actual border shrink
     * operation takes place. This property can be used to trigger UI elements or perform actions related to
     * the upcoming border changes.
     * </p>
     */
    private SimpleBooleanProperty aboutToShrink = new SimpleBooleanProperty(false);

    /**
     * Represents the time duration, in milliseconds, before the game borders start to shrink.
     *
     * <p>
     * The {@code shrinkWarningTime} constant defines the period in milliseconds during which
     * a warning is given before the game borders begin to shrink. In the Snake Game, the
     * borders shrink at regular intervals, and this constant determines the duration of the
     * warning phase in advance of the actual border shrinkage.
     * </p>
     */
    private final int shrinkWarningTime = 2; // Give a 2-second warning

    /**
     * Represents the screen width of the Snake Game grid.
     *
     * <p>
     * The {@code SCREEN_WIDTH} constant defines the width of the game grid, representing the horizontal
     * extent of the playable area in the Snake Game. It is used to set the initial size of the game window
     * and determine the rightmost limit of the game area.
     * </p>
     */
    public static int SCREEN_WIDTH = 1100;

    /**
     * Represents the screen height of the Snake Game grid.
     *
     * <p>
     * The {@code SCREEN_HEIGHT} constant defines the height of the game grid, representing the vertical
     * extent of the playable area in the Snake Game. It is used to set the initial size of the game window
     * and determine the lower limit of the game area.
     * </p>
     */
    public static int SCREEN_HEIGHT = 700;

    /**
     * Represents the bottom boundary of the game grid in the Snake Game.
     *
     * <p>
     * The {@code bottomBorder} variable holds the y-coordinate value that represents the
     * bottom boundary of the Snake Game grid. It defines the limit beyond which the snake
     * cannot move downward. This value is calculated based on the screen height and the
     * size of each unit in the grid.
     * </p>
     */
    private int bottomBorder = SCREEN_HEIGHT;


    /**
     * Represents the left border coordinate of the Snake Game grid.
     *
     * <p>
     * The {@code leftBorder} variable defines the x-coordinate of the left border in the Snake Game grid.
     * It is used to determine the leftmost limit of the game area and influences collision detection.
     * Modifying this variable may affect the starting position of the snake and the placement of game elements.
     * </p>
     */
    /**
     * Represents the left border coordinate of the Snake Game grid.
     *
     * <p>
     * The {@code leftBorder} variable represents the x-coordinate of the leftmost border of the game grid.
     * It is used to determine the left limit of the playable area in the Snake Game.
     * </p>
     */
    public int leftBorder = 0;


    /**
     * Represents the right border coordinate of the Snake Game grid.
     *
     * <p>
     * The {@code rightBorder} variable represents the x-coordinate of the rightmost border of the game grid.
     * It is used to determine the right limit of the playable area in the Snake Game.
     * </p>
     */
    private int rightBorder = SCREEN_WIDTH;


    /**
     * Represents the top border coordinate of the Snake Game grid.
     *
     * <p>
     * The {@code topBorder} variable defines the y-coordinate of the top border in the Snake Game grid.
     * It is used to determine the upper limit of the game area and influences collision detection.
     * Modifying this variable may affect the starting position of the snake and the placement of game elements.
     * </p>
     */
    public int topBorder = 0;

    /**
     * Represents the size of a single unit in the Snake grid.
     *
     * <p>
     * The {@code UNIT_SIZE} constant defines the size (in pixels) of a single unit in the Snake grid.
     * It is used to determine the size of the snake segments, the apples, and the spacing of the game grid.
     * Modifying this constant allows for easy adjustments to the overall scale of the game elements.
     * </p>
     */
    public static final int UNIT_SIZE = 50;
    private long playTime;
    static  int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    int[] x = new int[GAME_UNITS];
    int[] y = new int[GAME_UNITS];
    /**
     * Represents the game over state.
     *
     * <p>
     * The {@code gameOver} property is a boolean property that indicates whether the game is currently in a
     * game over state. When the game is over, it can be used to trigger UI updates or perform other actions
     * related to the end of the game.
     * </p>
     */
    private BooleanProperty gameOver = new SimpleBooleanProperty(false);

    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Random random = new Random();
    private int borderShrinkInterval = 10;
    private long lastShrinkTime = 0;

    /**
     * Represents the score of the Snake Game.
     *
     * <p>
     * The {@code score} property tracks the player's score in the Snake Game. It is updated
     * when the snake consumes an apple, and the score is displayed to the player.
     * </p>
     */
    private IntegerProperty score = new SimpleIntegerProperty(this, "score", 0);

    /**
     * Represents the length of the snake in the Snake Game.
     *
     * <p>
     * The {@code length} property defines the current length of the snake in the game. It
     * increases when the snake consumes an apple and decreases if the snake collides with
     * itself or the game borders.
     * </p>
     */
    private IntegerProperty length = new SimpleIntegerProperty(this, "length", 6);
    // Constants for special apple duration and refresh interval
    /**
     * Represents the duration, in milliseconds, for which a special apple exists in the Snake Game.
     * <p>
     * The {@code SPECIAL_APPLE_DURATION} constant defines the time duration for which a special apple
     * remains present in the game. After this duration, the special apple disappears, and its effects
     * on the game, such as speed changes, are no longer applied.
     * </p>
     */
    public final long SPECIAL_APPLE_DURATION = 5000; // Special apple existence time: 5 seconds


    /**
     * The speed multiplier property represents the factor by which the game's speed is adjusted.
     */
    public DoubleProperty speedMultiplier = new SimpleDoubleProperty(1.0); // default speed has something to do with UPDATE_INTERVAL

    /**
     * The specialAppleExists property indicates whether a special apple is currently present in the game.
     */
    public BooleanProperty specialAppleExists = new SimpleBooleanProperty(false);

    /**
     * Represents the timestamp when a change in the snake's speed will end.
     * <p>
     * The {@code speedChangeEndTime} field holds the system time (in milliseconds) when a change in the snake's
     * speed is scheduled to end. This timestamp is used to track the duration of speed changes caused by special apples,
     * allowing the game to revert to its default speed after a certain period.
     * </p>
     */
    public long speedChangeEndTime = 0;
    private Apple specialApple;

    public void setGameOver(boolean b) {
        gameOver.set(true);
    }


    /**
     * Increases the current score by a specified amount.
     * <p>
     * This method increments the score of the game by a fixed amount (3 points).
     * It is typically called when a player achieves a certain action, like collecting an item
     * or reaching a milestone in the game.
     * </p>
     * <p>
     * Note: The increment value is currently fixed at 3 points, regardless of the input parameter.
     * </p>
     *
     * @param i The amount to increase the score by (currently unused, as the increment is fixed).
     */
    public void increaseScore(int i) {
        score.set(score.get() + 3);
    }


    /**
     * Handles time updates by updating the play time and triggering border shrinkage.
     * <p>
     * This method is called whenever there is a time update. It updates the play time of
     * the game and triggers any necessary actions, such as shrinking the game border.
     * </p>
     *
     * @param time The updated time value.
     */
    @Override
    public void onTimeUpdate(long time) {
        this.playTime = time;
        shrinkBorder();
    }


    /**
     * Enum representing different types of apples that can appear in the game.
     */
    public enum AppleType {
        BLUE, BLACK, GREEN, GOLD
    }
    private int specialAppleX;
    private int specialAppleY;
    private AppleType specialAppleType;
    /**
     * Represents the timestamp when a special apple was spawned in the Snake Game.
     * <p>
     * The {@code specialAppleSpawnTime} field holds the system time (in milliseconds) when a special apple
     * was generated in the game. This timestamp is used to track the duration of the special apple's presence
     * in the game and to determine when it should be removed.
     * </p>
     */
    public long specialAppleSpawnTime;

    /**
     * Constructs a new instance of the {@code GameModel} class.
     * <p>
     * This constructor initializes the game state and borders, setting up the initial conditions for the Snake Game.
     * It specifically resets the game to its initial state, allowing for a fresh start.
     * </p>
     * <p>
     * The initialization includes creating the game board, placing the snake, and generating the initial food item.
     * </p>
     */
    private GameModel() {
        speedMultiplier.set(INITIAL_SPEED);
        currentState = runningState;
        lastShrinkTime = 0;
    }

    /**
     * Provides the singleton instance of the {@code GameModel}.
     * <p>
     * This method implements the singleton pattern to ensure that only one instance of the
     * {@code GameModel} is created and used throughout the application. If the instance is not
     * already created, it instantiates a new {@code GameModel} object and returns it.
     * </p>
     *
     * @return The singleton instance of the {@code GameModel}.
     */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }



    /**
     * Updates the game state, including moving the snake, checking for apple consumption,
     * checking for collisions, and handling border shrinking.
     */
    public void update() {
        currentState.update(this);
    }

    /**
     * Handles player input to control the snake's direction.
     * <p>
     * The {@code handleInput} method processes player input to change the direction of the snake in response to
     * user commands. It takes a character representing the input and adjusts the snake's movement accordingly.
     * The valid input characters are 'U' for up, 'D' for down, 'L' for left, and 'R' for right.
     * </p>
     *
     * @param input The character representing the player's input ('U', 'D', 'L', or 'R').
     */
    public void handleInput(char input) {
        currentState.handleInput(this, input);
    }

    /**
     * Changes the current game state to the specified new state.
     * <p>
     * The {@code changeState} method is responsible for transitioning the game from its current state to a new state.
     * It updates the {@code currentState} field with the provided {@code newState}, allowing for dynamic changes in
     * the game's behavior and logic. This method is typically used to switch between different states, such as the
     * running state and game over state.
     * </p>
     *
     * @param newState The new game state to set.
     */
    public void changeState(GameState newState) {
        currentState = newState;
    }


    /**
     * Shrinks the game borders periodically.
     * <p>
     * This method is responsible for reducing the size of the game borders at regular intervals. It checks if it's
     * time to shrink the borders and, if so, updates the borders and regenerates the apple if it is outside the new borders.
     * </p>
     * <p>
     * Additionally, it sets the 'aboutToShrink' flag to true if the borders are about to shrink within the next
     * 'shrinkWarningTime' milliseconds.
     * </p>
     */
    public void shrinkBorder() {
        if (playTime - lastShrinkTime > borderShrinkInterval - shrinkWarningTime) {
            aboutToShrink.set(true);
        } else {
            aboutToShrink.set(false);
        }
        if (playTime - lastShrinkTime > borderShrinkInterval) {
            leftBorder += UNIT_SIZE;
            rightBorder -= UNIT_SIZE;
            topBorder += UNIT_SIZE;
            bottomBorder -= UNIT_SIZE;
            lastShrinkTime = playTime;

            // Check if the apple is outside the new borders
            if (appleX < leftBorder || appleX >= rightBorder || appleY < topBorder || appleY >= bottomBorder) {
                newApple(); // Regenerate the apple
            }
        }
    }

    /**
     * Gets the x-coordinates of the snake.
     *
     * @return The x-coordinates of the snake segments.
     */
    public int[] getX() {
        return x;
    }

    /**
     * Gets the y-coordinates of the snake.
     *
     * @return The y-coordinates of the snake segments.
     */
    public int[] getY() {
        return y;
    }

    /**
     * Gets the number of body parts of the snake.
     *
     * @return The number of body parts.
     */
    public int getBodyParts() {
        return bodyParts;
    }

    /**
     * Gets the number of apples eaten by the snake.
     *
     * @return The number of apples eaten.
     */
    public int getApplesEaten() {
        return applesEaten;
    }

    /**
     * Gets the x-coordinate of the apple.
     *
     * @return The x-coordinate of the apple.
     */
    public int getAppleX() {
        return appleX;
    }

    /**
     * Gets the y-coordinate of the apple.
     *
     * @return The y-coordinate of the apple.
     */
    public int getAppleY() {
        return appleY;
    }

    /**
     * Checks if the game is currently running.
     *
     * @return {@code true} if the game is running, {@code false} otherwise.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the running state of the game.
     * <p>
     * This method is used to start or stop the game by changing the running state. A true
     * value starts the game, and a false value stops it.
     * </p>
     *
     * @param thing The new running state of the game. True to start, false to stop.
     */
    public void setRunning(boolean thing) {
        running = thing;
    }

    /**
     * Moves the snake based on the current direction.
     */
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    /**
     * Generates a special apple with a random type and position.
     */
    public void generateSpecialApple() {
        // Randomly select a special apple type
        AppleType[] appleTypes = AppleType.values();
        specialAppleType = appleTypes[random.nextInt(appleTypes.length)];

        while (true) {
            // Generate random coordinates for the new apple
            specialAppleX = random.nextInt((rightBorder - leftBorder) / UNIT_SIZE) * UNIT_SIZE + leftBorder;
            specialAppleY = random.nextInt((bottomBorder - topBorder) / UNIT_SIZE) * UNIT_SIZE + topBorder;

            // Check if the apple is within the borders
            if (specialAppleX >= leftBorder && specialAppleX < rightBorder && specialAppleY >= topBorder && specialAppleY < bottomBorder) {
                break; // The apple is within the borders, exit the loop
            }
        }

        // Use factory to create a new Apple object
        Apple specialApple = AppleFactory.createApple(specialAppleType, specialAppleX, specialAppleY);

        // Store the special apple in a field of GameModel
        this.specialApple = specialApple; // Assuming 'specialApple' is a field of type Apple
        specialAppleSpawnTime = System.currentTimeMillis();
        specialAppleExists.set(true);
    }

    /**
     * Removes the special apple by setting the existence flag to false.
     */
    public void removeSpecialApple() {
        specialAppleExists.set(false);
    }


    /**
     * Checks if the snake has eaten the apple and updates the game state accordingly.
     */
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            score.set(score.get() + 1);
            length.set(length.get() + 1);
            newApple();
        }
        // check if got a special apple
        if (x[0] == specialAppleX && y[0] == specialAppleY) {
            specialApple.applyEffect(this);
            removeSpecialApple();
        }
    }
    /**
     * Processes the effect of the special apple based on its type.
     *
     * @param appleType The type of the special apple.
     */
    private void processAppleEffect(AppleType appleType) {
        switch (appleType) {
            case BLUE:
                // Increase speed
                increaseSpeed();
                break;
            case BLACK:
                // gameover
                gameOver.set(true);
                break;
            case GREEN:
                // reduce speed
                decreaseSpeed();
                break;
            case GOLD:
                score.set(score.get() + 3);
                break;
            default:
                // Default logic for regular apples
                bodyParts++;
                applesEaten++;
                score.set(score.get() + 1);
                length.set(length.get() + 1);
                break;
        }
    }

    /**
     * Gets the type of the current special apple.
     *
     * @return The type of the special apple.
     */
    public AppleType getSpecialAppleType() {
        return specialAppleType;
    }

    /**
     * Increases the speed of the snake.
     */
    void increaseSpeed() {
        speedMultiplier.set(Math.min(speedMultiplier.get() + 0.25, MAX_SPEED)); // Increase speed moderately
        speedChangeEndTime = System.currentTimeMillis() + SPECIAL_APPLE_DURATION; // Set the end time for speed change
    }

    /**
     * Decreases the speed of the snake.
     */
    void decreaseSpeed() {
        speedMultiplier.set(Math.max(speedMultiplier.get() - 0.10, MIN_SPEED)); // Decrease speed moderately
        speedChangeEndTime = System.currentTimeMillis() + SPECIAL_APPLE_DURATION; // Set the end time for speed change
    }

    /**
     * Resets the speed to the default value.
     */
    public void resetSpeed() {
        speedMultiplier.set(1.0); // Reset to the default speed
    }

    /**
     * Gets the current speed multiplier value.
     *
     * @return The current speed multiplier.
     */
    public double getSpeedMultiplier() {
        return speedMultiplier.get();
    }

    /**
     * Returns the property representing the speed multiplier.
     *
     * @return The speed multiplier property.
     */
    public DoubleProperty speedMultiplierProperty() {
        return speedMultiplier;
    }

    /**
     * Checks for collisions, including collisions with the snake's own body and collisions with the game borders.
     * <p>
     * This method examines the current state of the game to identify any collisions that may have occurred. It checks
     * if the snake's head collides with its own body or if it reaches the game borders, indicating a collision and the
     * end of the game.
     * </p>
     * <p>
     * If a collision is detected, the method sets the 'running' flag to false, indicating that the game is no longer
     * in progress. Additionally, if the collision is with the borders, the 'gameOver' flag is set to true.
     * </p>
     */
    public void checkCollisions() {
        // Check if the head collides with the body
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                break;
            }
        }
        // Check if head touches left border
        if (x[0] < 0) running = false;
        // Check if head touches right border
        if (x[0] > SCREEN_WIDTH) running = false;
        // Check if head touches top border
        if (y[0] < 0) running = false;
        // Check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) running = false;
        // Check if head touches new borders
        if (x[0] < leftBorder || x[0] >= rightBorder || y[0] < topBorder || y[0] >= bottomBorder) {
            running = false;
        }

        if (!running) {
            changeState(gameOverState);
            gameOver.set(true);
        }
    }


    /**
     * Generates a new random apple within the game borders.
     * <p>
     * This method is responsible for creating a new apple at a random position within the specified game borders.
     * It ensures that the apple coordinates are aligned with the game grid, defined by the UNIT_SIZE.
     * </p>
     * <p>
     * Additionally, the method checks if the generated apple is within the game borders, preventing it from
     * being placed outside the playable area.
     * </p>
     */
    public void newApple() {
        while (true) {
            // Generate random coordinates for the new apple
            appleX = random.nextInt((rightBorder - leftBorder) / UNIT_SIZE) * UNIT_SIZE + leftBorder;
            appleY = random.nextInt((bottomBorder - topBorder) / UNIT_SIZE) * UNIT_SIZE + topBorder;

            // Check if the apple is within the borders
            if (appleX >= leftBorder && appleX < rightBorder && appleY >= topBorder && appleY < bottomBorder) {
                break; // The apple is within the borders, exit the loop
            }
        }

        // Logic to generate a special apple
        generateSpecialApple();
    }



    /**
     * Resets the game state to its initial configuration.
     * <p>
     * This method initializes various game parameters to their default values, providing a fresh start for the Snake Game.
     * It sets up the initial conditions, such as generating a new apple, resetting the snake's length and direction,
     * and initializing score-related variables.
     * </p>
     * <p>
     * Additionally, it resets the game board borders, allowing the snake to move freely within the game area.
     * </p>
     */
    public void resetGame() {
        playTime = 0;
        lastShrinkTime = 0;
        // Initialize game parameters
        newApple();
        applesEaten = 0;
        bodyParts = 6; // initial length
        direction = 'R'; // initial direction
        speedMultiplier.set(INITIAL_SPEED); // Reset the velocity to its initial value
        speedChangeEndTime = 0;
        running = true;
        gameOver.set(false);
        score.set(0);
        length.set(6);

        // Initialize snake position
        for (int i = 0; i < bodyParts; i++) {
            x[i] = -i * UNIT_SIZE; // Extend horizontally to the left
            y[i] = 0;
        }

        // Reset borders
        leftBorder = 0;
        rightBorder = SCREEN_WIDTH;
        topBorder = 0;
        bottomBorder = SCREEN_HEIGHT;
    }


    /**
     * Sets the direction of the snake.
     *
     * @param direction The new direction ('U' for up, 'D' for down, 'L' for left, 'R' for right).
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }

    /**
     * Gets the current direction of the snake.
     *
     * @return The current direction.
     */
    public char getDirection() {
        return this.direction;
    }

    /**
     * Gets the property representing the score of the game.
     *
     * @return The score property.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Gets the property representing the length of the snake.
     *
     * @return The length property.
     */
    public IntegerProperty lengthProperty() {
        return length;
    }

    public BooleanProperty gameOverProperty() {
        return gameOver;
    }

    public boolean isSpecialAppleExists() {
        return specialAppleExists.get();
    }

    /**
     * Gets the property that indicates whether a special apple exists in the game.
     *
     * @return The BooleanProperty for specialAppleExists.
     */
    public BooleanProperty specialAppleExistsProperty() {
        return specialAppleExists;
    }

    /**
     * Gets the y-coordinate of the special apple.
     *
     * @return The y-coordinate of the special apple.
     */
    public double getSpecialAppleY() {
        return specialAppleY;
    }

    /**
     * Gets the x-coordinate of the special apple.
     *
     * @return The x-coordinate of the special apple.
     */
    public double getSpecialAppleX() {
        return  specialAppleX;
    }

    /**
     * Gets the left border of the game area.
     *
     * @return The left border value.
     */
    public int getLeftBorder() {
        return leftBorder;
    }

    /**
     * Gets the right border of the game.
     *
     * @return The right border coordinate.
     */
    public int getRightBorder() {
        return rightBorder;
    }

    /**
     * Gets the top border of the game.
     *
     * @return The top border coordinate.
     */
    public int getTopBorder() {
        return topBorder;
    }

    /**
     * Gets the bottom border of the game.
     *
     * @return The bottom border coordinate.
     */
    public int getBottomBorder() {
        return bottomBorder;
    }

    /**
     * Gets the current score of the game.
     *
     * @return The current score.
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Checks if the game is about to shrink the borders.
     *
     * @return {@code true} if the borders are about to shrink, {@code false} otherwise.
     */
    public boolean isAboutToShrink() {
        return aboutToShrink.get();
    }

    /**
     * Gets the property representing whether the borders are about to shrink.
     *
     * @return The aboutToShrink property.
     */
    public BooleanProperty aboutToShrinkProperty() {
        return aboutToShrink;
    }

}

