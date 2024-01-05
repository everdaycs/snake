package com.example.cyc_snake.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

/**
 * Provides utility methods for playing music and sound effects in the Snake Game application.
 *
 * <p>
 * The {@code PlayMusic} class contains static methods to play various background music (BGM) and sound
 * effects. It utilizes the JavaFX {@code MediaPlayer} class to manage audio playback. The methods include
 * playing BGM during gameplay, initializing background music, playing sound effects for apple eating and
 * game over events, setting BGM volume, and playing a click sound for user interactions.
 * </p>
 *
 * <h2>Key Methods:</h2>
 * <ul>
 *   <li>{@code setBgmVolume(double volume)}: Sets the volume of the background music.</li>
 *   <li>{@code play2thBGM()}: Plays the second background music.</li>
 *   <li>{@code playGameBGM()}: Plays the main game background music.</li>
 *   <li>{@code initializeBGM()}: Initializes the background music for the initialization view.</li>
 *   <li>{@code eatAppleSound()}: Plays the sound effect for apple eating.</li>
 *   <li>{@code gameOverSound()}: Plays the sound effect for the game over event.</li>
 *   <li>{@code clickSound()}: Plays a click sound for user interactions.</li>
 *   <li>{@code disposeBGM2()}: Stops and disposes of the second background music.</li>
 *   <li>{@code disposeBGM1()}: Stops and disposes of the main game background music.</li>
 * </ul>
 *
 * @version 1.0
 * @since 2023-12-05
 */
public class PlayMusic {
    /** The media player for the sound effect of apple eating. */
    public static MediaPlayer appleEatingMediaPlayer;

    /** The media player for the sound effect of game over. */
    private static MediaPlayer gameOverMediaPlayer;

    /** The media player for the first background music (BGM). */
    public static MediaPlayer firstBGM;

    /** The media player for the second background music (BGM). */
    public static MediaPlayer secondBGM;

    /** The media player for the initialization background music (BGM). */
    public static MediaPlayer initializeBGM;

    /**
     * Default constructor for the {@code PlayMusic} class.
     * This constructor is used when an instance of the class is created without parameters.
     * Note: This constructor is provided for default instantiation and should not be used explicitly.
     */
    public PlayMusic() {
        // Default constructor provided for default instantiation.
    }

    /**
     * Sets the volume of the background music.
     *
     * @param volume The volume level (ranging from 0 to 100).
     */
    public static void setBgmVolume(double volume) {
        System.out.println("Set the volume");
        if (firstBGM != null) {
            firstBGM.setVolume(volume / 100);
        }
        if (secondBGM != null) {
            secondBGM.setVolume(volume / 100);
        }
    }

    /**
     * Plays the second background music.
     */
    public static void play2thBGM() {
        String newBGMPath = Objects.requireNonNull(PlayMusic.class.getResource("/Sounds/bgm2.mp3")).toExternalForm();
        Media newBGM = new Media(newBGMPath);
        secondBGM = new MediaPlayer(newBGM);
        secondBGM.setCycleCount(MediaPlayer.INDEFINITE);
        secondBGM.play();
    }

    /**
     * Plays the main game background music.
     */
    public static void playGameBGM() {
        String gameBGMPath = PlayMusic.class.getResource("/Sounds/game.mp3").toExternalForm();
        Media gameBGM = new Media(gameBGMPath);
        firstBGM = new MediaPlayer(gameBGM);
        firstBGM.setCycleCount(MediaPlayer.INDEFINITE);
        firstBGM.play();
    }

    /**
     * Initializes the background music for the initialization view.
     */
    public static void initializeBGM() {
        String initializeBGMPath = PlayMusic.class.getResource("/Sounds/UnchainedMelody.mp3").toExternalForm();
        Media startBGM = new Media(initializeBGMPath);
        initializeBGM = new MediaPlayer(startBGM);
        initializeBGM.setCycleCount(MediaPlayer.INDEFINITE);
        initializeBGM.play();
    }

    /**
     * Plays the sound effect for apple eating.
     */
    public static void eatAppleSound() {
        String appleEatingSoundPath = PlayMusic.class.getResource("/Sounds_Effects/apple_eating.wav").toExternalForm();
        Media appleEatingSound = new Media(appleEatingSoundPath);
        appleEatingMediaPlayer = new MediaPlayer(appleEatingSound);
        appleEatingMediaPlayer.play();
    }

    /**
     * Plays the sound effect for the game over event.
     */
    public static void gameOverSound() {
        String gameOverSoundPath = PlayMusic.class.getResource("/Sounds_Effects/dead.mp3").toExternalForm();
        Media gameOverSound = new Media(gameOverSoundPath);
        gameOverMediaPlayer = new MediaPlayer(gameOverSound);
        gameOverMediaPlayer.play();
    }

    /**
     * Plays a click sound for user interactions.
     */
    public static void clickSound() {
        String clickPath = PlayMusic.class.getResource("/Sounds_Effects/StartGame.mp3").toExternalForm();
        Media clickSound = new Media(clickPath);
        MediaPlayer clickSoundMedia = new MediaPlayer(clickSound);
        clickSoundMedia.play();
    }

    /**
     * Stops and disposes of the second background music.
     */
    public static void disposeBGM2() {
        secondBGM.stop();
        secondBGM.dispose();
    }

    /**
     * Stops and disposes of the main game background music.
     */
    public static void disposeBGM1() {
        firstBGM.stop();
        firstBGM.dispose();
    }
}
