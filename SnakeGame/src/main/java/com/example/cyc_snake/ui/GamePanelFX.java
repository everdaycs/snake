package com.example.cyc_snake.ui;

import com.example.cyc_snake.models.GameModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

/**
 * Custom UI component representing the game panel in the Snake Game application.
 *
 * <p>
 * The {@code GamePanelFX} class extends the JavaFX {@code Pane} class and is responsible for rendering the game
 * elements, including the snake, apple, and game borders. It provides methods to initialize the UI, draw the game
 * components on the canvas, and handle the background and snake colors. Additionally, it displays a warning message
 * when the game is about to shrink.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-22
 */
public class GamePanelFX extends Pane {

    private Color snakeColor = Color.GREEN;
    private Color backgroundColor = Color.WHITE; // Default background color

    private GameModel gameModel;
    private Canvas canvas;
    private GraphicsContext gc;
    // add special apple colors
    private Color blueAppleColor = Color.BLUE;
    private Color blackAppleColor = Color.BLACK;
    private Color yellowAppleColor = Color.YELLOW;
    private Color goldAppleColor = Color.GOLD;
    private final Image blueAppleImage;
    private final Image blackAppleImage;
    private final Image greenAppleImage;
    private final Image goldAppleImage;
    private final Image redAppleImage;
    private final Image snakeBodyRightImage;
    private final Image snakeBodyLeftImage;
    private final Image snakeBodyUpImage;

    private final Image snakeBodyDownImage;

    private final Image snakeHeadUpImage;
    private final Image snakeHeadRightImage;
    private final Image snakeHeadDownImage;
    private final Image snakeHeadLeftImage;


    /**
     * Constructs a new instance of the {@code GamePanelFX} class.
     */
    public GamePanelFX() {
        setFocusTraversable(true);

        redAppleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/RedApple.png")));
        blueAppleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/BlueApple.png")));
        blackAppleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/BlackApple.png")));
        greenAppleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/GreenApple.png")));
        goldAppleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/GoldApple.png")));
        //head
        snakeHeadUpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeUpHead.png")));
        snakeHeadRightImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeRightHead.png")));
        snakeHeadDownImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeDownHead.png")));
        snakeHeadLeftImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeLeftHead.png")));
        //body
        snakeBodyRightImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeRightBody.png")));
        snakeBodyDownImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeDownBody.png")));
        snakeBodyLeftImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeLeftBody.png")));
        snakeBodyUpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SnakeUpBody.png")));
    }

    /**
     * Requests focus for the game panel.
     */
    public void requestFocusForGamePanel() {
        requestFocus();
    }

    /**
     * Sets the game model for the game panel.
     *
     * @param model The game model to be set.
     */
    public void setGameModel(GameModel model) {
        this.gameModel = model;
        initializeUI();
        draw();
    }

    /**
     * Initializes the User Interface (UI) for the Snake Game.
     * This method sets up the canvas, graphics context (gc), and adds them to the UI container.
     * It also triggers the initial drawing of the game elements using the draw method.
     * Note: This method is typically called during the initialization phase to prepare the UI for displaying the game.
     */
    private void initializeUI() {
        // Create a new canvas with the specified width and height
        this.canvas = new Canvas(GameModel.SCREEN_WIDTH, GameModel.SCREEN_HEIGHT);

        // Get the 2D graphics context for the canvas
        this.gc = canvas.getGraphicsContext2D();

        // Add the canvas to the UI container
        getChildren().add(canvas);

        // Trigger the initial drawing of the game elements
        draw();
    }


    /**
     * Draws a rotated image on the graphics context.
     *
     * @param gc    The GraphicsContext on which to draw the image.
     * @param image The image to be drawn.
     * @param angle The angle of rotation in degrees.
     * @param tlpx  The x-coordinate of the top-left corner of the image.
     * @param tlpy  The y-coordinate of the top-left corner of the image.
     */
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // Save the current state
        double centerX = tlpx + image.getWidth() / 2;
        double centerY = tlpy + image.getHeight() / 2;
        gc.translate(centerX, centerY); // Move the rotation center to the image center
        gc.rotate(angle); // Rotate by the specified angle
        gc.drawImage(image, -image.getWidth() / 2, -image.getHeight() / 2); // Draw the image
        gc.restore(); // Restore to the previously saved state
    }

    /**
     * Draws the current state of the Snake Game on the canvas.
     * This method is responsible for rendering the game elements, including the snake, food, and any additional features.
     * It utilizes the GraphicsContext (gc) to draw on the canvas, considering the current game model state.
     * The method ensures proper visualization of the game components and their interactions.
     * Note: This method is typically called in a game loop to continuously update the visual representation of the game.
     */
    public void draw() {
        if (gameModel != null && gameModel.isRunning()) {
            Image appleImage;
            appleImage = redAppleImage;
            // Clear the canvas
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Draw the background
            gc.setFill(backgroundColor);
            gc.fillRect(0, 0, GameModel.SCREEN_WIDTH, GameModel.SCREEN_HEIGHT);

            // Draw the border
            gc.setStroke(Color.BLACK); // Set border color
            gc.setLineWidth(3); // Set border width
            gc.strokeRect(gameModel.getLeftBorder(), gameModel.getTopBorder(),
                    gameModel.getRightBorder() - gameModel.getLeftBorder(),
                    gameModel.getBottomBorder() - gameModel.getTopBorder());

            // Draw the apple
            gc.drawImage(appleImage, gameModel.getAppleX(), gameModel.getAppleY(), GameModel.UNIT_SIZE, GameModel.UNIT_SIZE);
            // draw special apple
            drawSpecialApple();
            // Draw the snake
            for (int i = 0; i < gameModel.getBodyParts(); i++) {
                Image segmentImage;
                if (i == 0) {
                    // Select the correct head image based on the direction
                    switch (gameModel.getDirection()) {
                        case 'U': segmentImage = snakeHeadUpImage; break;
                        case 'R': segmentImage = snakeHeadRightImage; break;
                        case 'D': segmentImage = snakeHeadDownImage; break;
                        case 'L': segmentImage = snakeHeadLeftImage; break;
                        default: segmentImage = snakeHeadUpImage; // Default is up
                    }
                } else {
                    // Select the correct body image based on the direction of the segment relative to the previous one
                    int dx = gameModel.getX()[i] - gameModel.getX()[i - 1];
                    int dy = gameModel.getY()[i] - gameModel.getY()[i - 1];
                    if (dx > 0) {
                        // Body segment to the left of the previous segment, so it's facing right
                        segmentImage = snakeBodyRightImage;
                    } else if (dx < 0) {
                        // Body segment to the right of the previous segment, so it's facing left
                        segmentImage = snakeBodyLeftImage;
                    } else if (dy > 0) {
                        // Body segment above the previous segment, so it's facing down
                        segmentImage = snakeBodyDownImage;
                    } else {
                        // Body segment below the previous segment, so it's facing up
                        segmentImage = snakeBodyUpImage;
                    }
                }
                // Draw the segment
                gc.drawImage(segmentImage, gameModel.getX()[i], gameModel.getY()[i], GameModel.UNIT_SIZE, GameModel.UNIT_SIZE);
            }
            if (gameModel.isRunning() && gameModel.isAboutToShrink()) {
                drawShrinkWarning();
            }
        }
    }

    /**
     * Draws the special apple on the canvas if it exists in the game model.
     */
    private void drawSpecialApple() {
        if (gameModel != null && gameModel.isSpecialAppleExists()) {
            // Choose the color based on the special apple type
            Image appleImage;
            switch (gameModel.getSpecialAppleType()) {
                case BLUE:
                    appleImage = blueAppleImage;
                    break;
                case BLACK:
                    appleImage = blackAppleImage;
                    break;
                case GREEN:
                    appleImage = greenAppleImage;
                    break;
                case GOLD:
                    appleImage = goldAppleImage;
                    break;
                default:
                    return; // Do not draw if there is no matching type
            }
            // Draw the special apple
            gc.drawImage(appleImage, gameModel.getSpecialAppleX(), gameModel.getSpecialAppleY(), GameModel.UNIT_SIZE, GameModel.UNIT_SIZE);
        }
    }



    /**
     * Draws a warning message on the canvas indicating that the snake square is about to shrink,
     * instructing the player to evacuate.
     * This method temporarily modifies the canvas state to set the font, text color, and alignment for the warning message.
     * The canvas state is saved before modifications and restored afterward.
     */
    private void drawShrinkWarning() {
        gc.save(); // Save the current canvas state
        gc.setFont(new Font("Arial", 24)); // Set font and size
        gc.setFill(snakeColor); // Set text color to green
        gc.setTextAlign(TextAlignment.CENTER); // Set text alignment to center
        gc.fillText("The square is about to shrink, please evacuate!", (double) GameModel.SCREEN_WIDTH / 2, (double) GameModel.SCREEN_HEIGHT / 10);
        gc.restore(); // Restore the canvas to the previously saved state
    }


    /**
     * Sets the background color for the game panel.
     *
     * @param color The background color to be set.
     */
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    /**
     * Sets the snake color for the game panel.
     *
     * @param color The snake color to be set.
     */
    public void setSnakeColor(Color color) {
        this.snakeColor = color;
    }

    /**
     * Gets the background color used in the Snake Game.
     *
     * @return The background color of the game.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Gets the color of the snake in the Snake Game.
     *
     * @return The color of the snake.
     */
    public Color getSnakeColor() {
        return snakeColor;
    }

}
