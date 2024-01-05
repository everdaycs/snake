package com.example.cyc_snake;

import com.example.cyc_snake.models.Player;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database connections and operations for the Snake Game application.
 *
 * <p>
 * This class, {@code DatabaseConnection}, is responsible for managing database connections and executing
 * various operations related to player data and game records. It includes methods to retrieve player rankings,
 * unique player names, save game records, delete players, get the highest score, and export data to a CSV file.
 * </p>
 *
 * <p>
 * The class uses JDBC to connect to a MySQL database and perform SQL queries. It provides methods to interact with
 * the database, retrieve data, and perform necessary operations for the game's functionality.
 * </p>
 *
 * @version 1.0
 * @since 2023-11-27
 */
public class DatabaseConnection {
    // JDBC connection parameters
    static String url = "jdbc:mysql://rm-cn-pe33ifwp30001ao.rwlb.rds.aliyuncs.com:3306/game_record";
    static String user = "unnc_dms";
    static String password = "cychhh123AAA!";

    // Ensure the MySQL driver is loaded when the class is initialized
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of player rankings from the database.
     *
     * @return A list of Player objects representing player rankings.
     */
    public static List<Player> getPlayerRankings() {
        List<Player> rankings = new ArrayList<>();
        String sql = "SELECT playerName, gamescore, GameTimeInMilliseconds FROM gamescores ORDER BY gamescore DESC, GameTimeInMilliseconds ASC";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String playerName = resultSet.getString("playerName");
                int score = resultSet.getInt("gamescore");
                long gameTime = resultSet.getLong("GameTimeInMilliseconds");

                rankings.add(new Player(playerName, score, gameTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rankings;
    }

    /**
     * Default constructor for the {@code DatabaseConnection} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public DatabaseConnection() {
        // Default constructor provided for default instantiation.
    }

    /**
     * Retrieves a list of unique player names from the database.
     *
     * @return A list of unique player names.
     */
    public static List<String> getUniquePlayerNames() {
        List<String> playerNames = new ArrayList<>();
        String sql = "SELECT DISTINCT playerName FROM gamescores WHERE playerName IS NOT NULL";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                playerNames.add(resultSet.getString("playerName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerNames;
    }

    /**
     * Saves a game record to the database.
     *
     * @param playerName The name of the player.
     * @param score      The score achieved in the game.
     * @param playTime   The duration of the game in milliseconds.
     */
    public static void saveGameRecord(String playerName, int score, long playTime) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO gamescores (playerName, gamescore, GameTimeInMilliseconds) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, playerName);
                preparedStatement.setInt(2, score);
                preparedStatement.setLong(3, playTime);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a player from the database.
     *
     * @param playerName The name of the player to be deleted.
     * @throws SQLException If an SQL exception occurs.
     */
    public static void deletePlayer(String playerName) throws SQLException {
        String sql = "DELETE FROM gamescores WHERE playerName = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, playerName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Exports data from the database to a CSV file.
     *
     * @param fileName The name of the CSV file.
     */
    public static void exportDataToCSV(String fileName) {
        // Print the current working directory
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        String directoryPath = "data"; // Target folder
        String filePath = directoryPath + File.separator + fileName;
        File directory = new File(directoryPath);

        // If the data directory does not exist, try to create it
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory: " + directory.getAbsolutePath());
                return; // If the directory creation fails, stop execution
            }
        }

        File csvFile = new File(filePath);
        // Print the absolute path of the file
        System.out.println("Absolute Path = " + csvFile.getAbsolutePath());

        // If a CSV file with the same name exists, delete it
        if (csvFile.exists() && csvFile.delete()) {
            System.out.println("Deleted existing CSV file: " + csvFile.getAbsolutePath());
        }

        String sql = "SELECT PlayerID, PlayerName, GameScore, GameTimeInMilliseconds FROM gamescores ORDER BY GameScore DESC, GameTimeInMilliseconds ASC";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();
             PrintWriter writer = new PrintWriter(filePath)) {

            writer.println("PlayerID,PlayerName,GameScore,GameTimeInMilliseconds");

            // Verify data is written
            boolean hasData = false;

            while (resultSet.next()) {
                hasData = true;
                int playerId = resultSet.getInt("PlayerID");
                String playerName = resultSet.getString("PlayerName");
                int score = resultSet.getInt("GameScore");
                long gameTime = resultSet.getLong("GameTimeInMilliseconds");

                writer.println(playerId + "," + playerName + "," + score + "," + gameTime);
            }

            if (!hasData) {
                System.out.println("No data to write to CSV file, query result is empty.");
            } else {
                System.out.println("Data successfully written to CSV file.");
            }
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error writing file:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown error:");
            e.printStackTrace();
        }
    }

}
