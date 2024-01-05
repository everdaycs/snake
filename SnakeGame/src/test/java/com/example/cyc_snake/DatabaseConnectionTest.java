package com.example.cyc_snake;

import com.example.cyc_snake.models.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Test class for DatabaseConnection.
 */
public class DatabaseConnectionTest {

    @BeforeEach
    void setUp() {
        // Initialize the test environment, such as connecting to a test database
    }

    @AfterEach
    void tearDown() {
        // Clean up the test environment, such as closing database connections
    }

    /**
     * Test the getPlayerRankings method.
     */
    @Test
    void testGetPlayerRankings() {
        // Call the getPlayerRankings method
        List<Player> rankings = DatabaseConnection.getPlayerRankings();

        // Verify that the result is as expected
        assertNotNull(rankings, "Rankings list should not be null");
        // Further tests can be performed based on the data in the test database
    }

    /**
     * Test the getUniquePlayerNames method.
     */
    @Test
    void testGetUniquePlayerNames() {
        // Call the getUniquePlayerNames method
        List<String> playerNames = DatabaseConnection.getUniquePlayerNames();

        // Verify that the result is as expected
        assertNotNull(playerNames, "Player names list should not be null");
        // Ensure no duplicate names are present
        assertEquals(playerNames.size(), playerNames.stream().distinct().count(),
                "Player names should be unique");
    }

    /**
     * Test the deletePlayer method.
     */
    @Test
    void testDeletePlayer() {
        String playerNameToDelete = "TestPlayer";

        // Ensure the player exists before deletion
        // This may involve adding a player first or ensuring the test database has this player

        // Call deletePlayer method
        assertDoesNotThrow(() -> DatabaseConnection.deletePlayer(playerNameToDelete),
                "Deleting a player should not throw an exception");

        // Verify the player has been removed
        List<String> playerNames = DatabaseConnection.getUniquePlayerNames();
        assertFalse(playerNames.contains(playerNameToDelete),
                "Deleted player should not exist in the database");
    }

    /**
     * Test the exportDataToCSV method.
     */
    @Test
    void testExportDataToCSV() {
        String testFileName = "test_export.csv";

        // Call exportDataToCSV method
        assertDoesNotThrow(() -> DatabaseConnection.exportDataToCSV(testFileName),
                "Exporting data to CSV should not throw an exception");

        // Verify the CSV file exists and contains the expected data
        // This may involve checking the file system and reading the CSV file
    }

    // More test cases can be added here to cover other methods
}
