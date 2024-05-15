package com.iantapply.wynncraft.logger;

import com.iantapply.wynncraft.configuration.PluginConfiguration;
import org.bukkit.Bukkit;

/**
 * A utility class intended to log messages easily to the Bukkit console
 */
public class Logger {
    /**
     * Log a message to the console with STDOUT
     * @param message The message to log
     */
    public static void logWithSTDOUT(String message) {
        System.out.println(message);
    }

    /**
     * Log a message to the console
     * @param level The logging level
     * @param message The message to log
     */
    public static void log(LoggingLevel level, String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(level.getColor() + "[" + level.getName() + "] " + message);
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
        log(LoggingLevel.INFO, "Version: " + PluginConfiguration.VERSION);
        log(LoggingLevel.INFO, "Developers: " + PluginConfiguration.DEVELOPER_CREDITS);
    }

    /**
     * Logs a shutdown message to the Bukkit console
     */
    public static void logShutdown() {
        log(LoggingLevel.INFO, "WynnCraft core plugin has been shutdown gracefully");
    }
}
