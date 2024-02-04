package ru.deknil.pseudo3d;

import ru.deknil.pseudo3d.core.Window;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 04.02.2024
 * @Description Main application entry point class
 * <p></p>
 * Default (Template) Project © 2024. All rights reserved.
 */
public class Main {
    /**
     * The main method of the application.
     * It creates a Window object and starts it in a new thread.
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create a new instance of the Window class
        Window window = new Window();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(window, 1000, Config.GAME_SPEED, TimeUnit.MILLISECONDS);
    }
}