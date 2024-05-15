package com.iantapply.wynncraft.logger;

import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * The different levels of logging that can be used in the plugin
 * Contains the name and color of the logging level to use in the Bukkit console
 */
@Getter
public enum LoggingLevel {
    WARNING("WARNING", NamedTextColor.RED),
    INFO("INFO", NamedTextColor.YELLOW),
    ERROR("ERROR", NamedTextColor.DARK_RED),
    DEBUG("DEBUG", NamedTextColor.WHITE);

    private final String name;
    private final NamedTextColor color;

    /**
     * Create a new logging level
     * @param name The name of the logging level to use in the console
     * @param color The Minecraft chat color of the logging level
     */
    LoggingLevel(String name, NamedTextColor color) {
        this.name = name;
        this.color = color;
    }

}