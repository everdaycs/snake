package com.example.cyc_snake.managers;

/**
 * An interface for objects that need to observe and respond to time updates.
 * <p>
 * Implementing classes will receive notifications about time updates, allowing them
 * to perform actions based on the current time.
 * </p>
 */
public interface TimeObserver {
    /**
     * Called when there is a time update.
     *
     * @param time The current time, usually expressed as milliseconds or ticks.
     */
    void onTimeUpdate(long time);
}
