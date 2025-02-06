package com.iantapply.wynncraft.logger;

import com.iantapply.wynncraft.configuration.PluginConfigurations;
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
        log(LoggingLevel.INFO, "Version: " + PluginConfigurations.VERSION);
        log(LoggingLevel.INFO, "Developers: " + PluginConfigurations.DEVELOPER_CREDITS);
    }

    /**
     * Logs an update notification to the Bukkit console
     */
    public static void logUpdateNotificationConsole() {
        log(LoggingLevel.INFO,"A new update is available for " + PluginConfigurations.NAME + " plugin");
        log(LoggingLevel.INFO, "You can find the update at: https://hangar.papermc.io/GucciFox/Wynncraft");
    }

    /**
     * Logs an unreleased plugin version notification to the Bukkit console
     */
    public static void logUnreleasedVersionNotification() {
        log(LoggingLevel.INFO, "The version for the " + PluginConfigurations.NAME + " plugin is higher than latest version");
        log(LoggingLevel.INFO, "You are currently running an unreleased version of the plugin that is NOT stable");
    }

    /**
     * Logs a shutdown message to the Bukkit console
     */
    public static void logShutdown() {
        log(LoggingLevel.INFO, "Wynncraft core plugin has been shutdown gracefully");
    }
}
