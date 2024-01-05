package com.example.cyc_snake.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

/**
 * Test class for the {@link GameViewController}.
 *
 * @see GameViewController
 * @author wjscyc
 * @date 2023-12-14 20:46
 * @version V1.0
 */
@ExtendWith(ApplicationExtension.class)
public class GameViewControllerTest {

    private GameViewController controller;

    @Start
    private void setUp(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cyc_snake/GameView.fxml"));
        Parent mainNode = loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @BeforeEach
    public void setUpClass() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Test
    public void testPlaytimeLabelStartsAtZero() {
        // Given: The application is started

        // When: No playtime has elapsed

        // Then: The timeLabel should display "Playtime: 0"
        verifyThat("#timeLabel", hasText("Playtime: 0"));
    }

    // Add more test methods as needed...

}
