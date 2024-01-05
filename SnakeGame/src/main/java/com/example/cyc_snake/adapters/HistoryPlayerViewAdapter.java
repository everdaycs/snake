package com.example.cyc_snake.adapters;

import com.example.cyc_snake.MainApp;
import com.example.cyc_snake.controllers.HistoryPlayerViewController;
import com.example.cyc_snake.managers.StateManager;

/**
 * Adapter class for connecting the HistoryPlayerViewController with the application's main components.
 *
 * <p>
 * The {@code HistoryPlayerViewAdapter} class acts as an adapter to initialize and connect the
 * {@code HistoryPlayerViewController} with the {@code StateManager} and {@code MainApp} instances.
 * </p>
 *
 * @author wjscyc
 * @version 1.0
 * @since 12/12/2023
 */
public class HistoryPlayerViewAdapter {
    private HistoryPlayerViewController controller;
    private StateManager stateManager;
    private MainApp mainApp;

    /**
     * Constructs a HistoryPlayerViewAdapter with the specified controller, state manager, and main app.
     *
     * @param controller   The {@code HistoryPlayerViewController} instance to be adapted.
     * @param stateManager The {@code StateManager} instance for managing application state.
     * @param mainApp      The {@code MainApp} instance representing the main application.
     */
    public HistoryPlayerViewAdapter(HistoryPlayerViewController controller, StateManager stateManager, MainApp mainApp) {
        this.controller = controller;
        this.stateManager = stateManager;
        this.mainApp = mainApp;
    }

    /**
     * Initializes the associated controller by setting its main application and state manager.
     */
    public void initializeController() {
        controller.setMainApp(mainApp);
        controller.setStateManager(stateManager);
    }
}
