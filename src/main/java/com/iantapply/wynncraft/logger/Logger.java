package com.iantapply.wynncraft.logger;

import com.iantapply.wynncraft.configuration.Plugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

/**
 * A utility class intended to log messages easily to the Bukkit console
 */
public class Logger {
    /**
     * Deprecated method to log a message to the console with STDOUT.
     * The preferred method to log is with the log method which uses the Adventure API.
     * @param message The message to log
     */
    @Deprecated
    public static void logWithSTDOUT(String message) {
        System.out.println(message);
    }

    /**
     * Log a message to the console
     * @param level The logging level
     * @param message The message to log
     */
    public static void log(LoggingLevel level, String message) {
        Component logMessage = Component.text()
                .append(Component.text("[" + level.getName() + "] ")
                        .color(level.getColor()))
                .append(Component.text(message))
                .build();

        Bukkit.getServer().getConsoleSender().sendMessage(logMessage);
    }

    /**
     * Logs an initialization message to the Bukkit console
     */
    public static void logInitialization() {
        log(LoggingLevel.INFO, "Initializing Wynncraft core plugin...");
    }

    /**
     * Logs a startup message to the Bukkit console containing plugin information
     */
    public static void logStartup() {
        log(LoggingLevel.INFO, "Wynncraft core plugin has initialized");
        log(LoggingLevel.INFO, "Version: " + Plugin.VERSION);
        log(LoggingLevel.INFO, "Developers: " + Plugin.DEVELOPER_CREDITS);
    }

    /**
     * Logs a shutdown message to the Bukkit console
     */
    public static void logShutdown() {
        log(LoggingLevel.INFO, "WynnCraft core plugin has been shutdown gracefully");
    }
}
