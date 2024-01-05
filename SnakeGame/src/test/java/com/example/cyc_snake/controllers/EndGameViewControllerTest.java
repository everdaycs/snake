package com.example.cyc_snake.controllers;

import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.managers.StateManager;
import com.example.cyc_snake.models.GameModel;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the {@link EndGameViewController}.
 *
 * @see EndGameViewController
 * @author wjscyc
 * @date 2023-12-15 16:43
 * @version V1.0
 */
class EndGameViewControllerTest {

    @Mock
    private MainApp mainApp;
    @Mock
    private StateManager stateManager;
    @Mock
    private GameModel gameModel;

    private EndGameViewController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Manually create and initialize the Label control
        controller = new EndGameViewController();
        controller.scoreLabel = new Label(); // Manually initialize

        controller.setMainApp(mainApp);
        controller.setStateManager(stateManager);
        controller.setGameModel(gameModel);
    }

    @Test
    void testHandleRestart() throws IOException {
        controller.handleRestart();
        verify(mainApp).showGameView();
    }

    @Test
    void testHandleBackToMenu() {
        controller.handleBackToMenu();
        verify(mainApp).showStartMenu();
    }

    @Test
    void testSetGameOverInfo() {
        when(gameModel.getScore()).thenReturn(100);
        controller.setGameOverInfo(100);
        // Verify logic or method calls
        // Since the setGameOverInfo method only sets text, you might need to consider a UI testing framework to verify its effect
    }

    // ... Other test cases
}
