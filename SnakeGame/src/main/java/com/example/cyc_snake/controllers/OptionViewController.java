package com.example.cyc_snake.controllers;

import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import com.example.cyc_snake.utils.PlayMusic;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

import java.io.IOException;

import static com.example.cyc_snake.utils.PlayMusic.clickSound;

/**
 * Controls the behavior of the settings view.
 *
 * <p>
 * The {@code SettingsController} class manages user interactions in the settings view. It allows users to
 * customize the background color and adjust background music volume. The class provides methods for handling
 * these interactions and updating the UI accordingly.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-21
 */
public class OptionViewController {
    private MainApp mainApp;
    private static final Color CORAL = Color.web("#FF7F50");
    private static final Color MINT_GREEN = Color.web("#98FF98");
    private static final Color DEEP_SEA_BLUE = Color.web("#00688B");
    private static final Color SOFT_YELLOW = Color.web("#FFFF99");
    private static final Color DEEP_PURPLE = Color.web("#800080");
    private static final Color TAUPE = Color.web("#483C32");
    private static final Color POWDER_BLUE = Color.web("#B0E0E6");
    private static final Color CREAM = Color.web("#FFFDD0"); // A color close to cream
    private static final Color BRIGHTBLUE = Color.web("#0000FF"); // A bright blue color

    @FXML private ComboBox<Color> backgroundColorComboBox;
    @FXML private Button applySettingsButton;
    @FXML private Slider volumeSlider;
    @FXML private Button startMenuButton;
    @FXML private AnchorPane backgroundPane;
    @FXML private Slider bgmVolumeSlider; // Background music volume control slider
    private StateManager stateManager;

    /**
     * Default constructor for the OptionViewController.
     * This constructor is automatically called by JavaFX during the controller initialization.
     * It is recommended not to include parameters in this constructor as JavaFX initializes controllers using this constructor.
     */
    public OptionViewController() {
        // Default constructor
        // Note: Avoid including parameters in this constructor as JavaFX initializes controllers using this constructor.
    }

    /**
     * Initializes the controller with the specified state manager.
     *
     * @param stateManager The state manager to be set.
     */
    public OptionViewController(StateManager stateManager) {
        this.stateManager = stateManager;
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
     * Updates the background color of the settings view.
     *
     * @param color The new background color.
     */
    private void updateBackgroundColor(Color color) {
        Platform.runLater(() -> {
            backgroundPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    /**
     * Initializes the settings view components.
     */
    @FXML
    private void initialize() {
        backgroundColorComboBox.getItems().addAll(
                Color.WHITE,
                Color.SKYBLUE,
                Color.PALEGREEN,
                Color.LAVENDER,
                CREAM,
                Color.LIGHTGRAY,
                CORAL,
                MINT_GREEN,
                DEEP_SEA_BLUE,
                SOFT_YELLOW,
                DEEP_PURPLE,
                TAUPE,
                POWDER_BLUE
        );

        backgroundColorComboBox.setConverter(new StringConverter<Color>() {
            @Override
            public String toString(Color color) {
                return getColorNameByColor(color);
            }

            @Override
            public Color fromString(String s) {
                return null;
            }
        });

        backgroundColorComboBox.setCellFactory(listView -> new ListCell<Color>() {
            @Override
            protected void updateItem(Color color, boolean empty) {
                super.updateItem(color, empty);
                if (color == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Rectangle colorRect = new Rectangle(10, 10, color);
                    setText(getColorNameByColor(color));
                    setGraphic(colorRect);
                }
            }
        });

    }

    /**
     * Handles the event when the "Apply Settings" button is clicked.
     */
    @FXML
    private void handleApplySettings() {
        Color selectedBackgroundColor = backgroundColorComboBox.getValue();
        applyBackgroundColor(selectedBackgroundColor);
        stateManager.setBackgroundColor(selectedBackgroundColor);
    }

    /**
     * Applies the selected background color to the view.
     *
     * @param selectedBackgroundColor The selected background color.
     */
    private void applyBackgroundColor(Color selectedBackgroundColor) {
        clickSound();
        backgroundPane.setBackground(new Background(new BackgroundFill(selectedBackgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }



    /**
     * Handles the event when the "Back to Menu" button is clicked.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void handleBackToMenu() throws IOException {
        clickSound();
        mainApp.showStartMenu();
    }

    /**
     * Gets the name of the color based on its value.
     *
     * @param color The color.
     * @return The name of the color.
     */
    public String getColorNameByColor(Color color) {
        if (color == null) {
            return "";
        } else if (color.equals(Color.WHITE)) {
            return "White";
        } else if (color.equals(Color.SKYBLUE)) {
            return "Sky Blue";
        } else if (color.equals(Color.PALEGREEN)) {
            return "Pale Green";
        } else if (color.equals(Color.LAVENDER)) {
            return "Lavender";
        } else if (color.equals(CREAM)) {
            return "Cream";
        } else if (color.equals(Color.LIGHTGRAY)) {
            return "Light Gray";
        } else if (color.equals(CORAL)) {
            return "Coral";
        } else if (color.equals(MINT_GREEN)) {
            return "Mint Green";
        } else if (color.equals(DEEP_SEA_BLUE)) {
            return "Deep Sea Blue";
        } else if (color.equals(SOFT_YELLOW)) {
            return "Soft Yellow";
        } else if (color.equals(DEEP_PURPLE)) {
            return "Deep Purple";
        } else if (color.equals(TAUPE)) {
            return "Taupe";
        } else if (color.equals(POWDER_BLUE)) {
            return "Powder Blue";
        } else {
            return "Unknown";
        }
    }
}
