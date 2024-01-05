/**
 * Module-info for the Snake Game application.
 *
 * <p>
 * This module-info file specifies the dependencies and configurations for the Snake Game
 * application. It declares the required modules, opens specific packages to the JavaFX FXML
 * module, and exports packages to other modules. Additionally, it declares dependencies on
 * JavaFX, ControlsFX, FormsFX, BootstrapFX, JavaFX Graphics, Java SQL, and JavaFX Media modules.
 * </p>
 *
 * @since 2023-11-30
 * @version 1.0
 */
module com.example.snake_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.media;


    opens com.example.cyc_snake.controllers to javafx.fxml;
    exports com.example.cyc_snake;
    exports com.example.cyc_snake.controllers;
    exports com.example.cyc_snake.ui to javafx.fxml;
    exports com.example.cyc_snake.models;
}
